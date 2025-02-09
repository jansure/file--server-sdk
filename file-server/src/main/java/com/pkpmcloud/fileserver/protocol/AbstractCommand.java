package com.pkpmcloud.fileserver.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pkpmcloud.fileserver.conn.Connection;
import com.pkpmcloud.fileserver.constant.OtherConstants;
import com.pkpmcloud.fileserver.exception.FastDfsIOException;
import com.pkpmcloud.fileserver.utils.BytesUtil;
import com.pkpmdesktopcloud.redis.RedisCache;

/**
 * FastDFS命令操执行抽象类
 * 作者：LiZW <br/>
 * 创建时间：2016/11/20 12:23 <br/>
 */
public abstract class AbstractCommand<T> implements BaseCommand<T> {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractCommand.class);

    /**
     * 请求对象
     */
    protected BaseRequest request;

    /**
     * 响应对象
     */
    protected BaseResponse<T> response;

    /**
     * 对服务端发出请求然后接收反馈
     */
    public T execute(Connection conn) {
        // 封装socket交易 send
        try {
            send(conn.getOutputStream(), conn.getCharset());
        } catch (IOException e) {
            throw new FastDfsIOException("Socket IO异常 发送消息异常", e);
        }
        try {
            return receive(conn.getInputStream(), conn.getCharset());
        } catch (IOException e) {
            throw new FastDfsIOException("Socket IO异常 接收消息异常", e);
        }
    }

    /**
     * 将报文输出规范为模板方法<br/>
     * 1.输出报文头<br/>
     * 2.输出报文参数<br/>
     * 3.输出文件内容<br/>
     */
    private void send(OutputStream out, Charset charset) throws IOException {
        // 报文分为三个部分
        // 报文头
        byte[] head = request.getHeadByte(charset);
        // 请求参数
        byte[] param = request.encodeParam(charset);
        // 交易文件流
        InputStream inputFile = request.getInputFile();
        long fileSize = request.getFileSize();
        logger.debug("发出请求 - 报文头[{}], 请求参数[{}]", request.getHead(), param);
        // 输出报文头
        out.write(head);
        // 输出交易参数
        if (null != param) {
            out.write(param);
        }
        // 输出文件流
        if (null != inputFile) {
            sendFileContent(inputFile, fileSize, out);
        }
    }

    /**
     * 发送文件
     */
    private void sendFileContent(InputStream ins, long size, OutputStream ous) throws IOException {
        logger.debug("开始上传文件流, 大小为[{}]", size);
        String md5 = BytesUtil.threadLocalMd5.get();
        
        long remainBytes = size;
        long uploadBytes = 0;//总上传数
        
        byte[] buff = new byte[256 * 1024];
        int bytes;
        while (remainBytes > 0) {
            if ((bytes = ins.read(buff, 0, remainBytes > buff.length ? buff.length : (int) remainBytes)) < 0) {
                throw new IOException("数据流已结束, 不匹配预期的大小");
            }
            ous.write(buff, 0, bytes);
            remainBytes -= bytes;
            logger.debug("剩余上传数据量[{}]", remainBytes);
            
            uploadBytes += bytes;
            int uploadPercent = (int) (uploadBytes * 100 /size);
            
            //多人同时上传同一文件只保留最快进度
            putMaxPercent(uploadPercent, md5);
            
        }
        
        //清空
        BytesUtil.threadLocalMd5.remove();
    }
    
      
	/**  
	 * @Title: putMaxPercent  
	 * @Description: 保存最大进度到Redis缓存
	 * @param uploadPercent   进度  
	 * @param md5    文件Md5
	 * @throws  
	 */  
	private void putMaxPercent(int uploadPercent, String md5) {
		
		try {
			RedisCache cache = new RedisCache(OtherConstants.FILE_UPLOAD_PERCENT_REDIS_KEY);
			Integer percent = (Integer)cache.getObject(md5);
			if(percent == null || percent.intValue() < uploadPercent) {
				
//				if(uploadPercent == 100) {
//					cache.setTimeOut(60);//1分钟后删除
//				}
				
				cache.putObject(md5, uploadPercent);
			}
			
			logger.debug("上传进度[{}]%", cache.getObject(md5));
		} catch (Exception ex){
			logger.error(ex.getMessage());
		}
		
	}

	/**
     * 接收相应数据,这里只能解析报文头
     * 报文内容(参数+文件)只能靠接收对象(对应的Response对象)解析
     */
    private T receive(InputStream in, Charset charset) throws IOException {
        // 解析报文头
        ProtocolHead head = ProtocolHead.createFromInputStream(in);
        logger.debug("服务端返回报文头{}", head);
        // 校验报文头
        head.validateResponseHead();
        // 解析报文体
        return response.decode(head, in, charset);
    }
}