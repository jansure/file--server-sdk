package com.pkpmcloud.fileserver.protocol.tracker;

import com.pkpmcloud.fileserver.conn.Connection;
import com.pkpmcloud.fileserver.testbase.GetTrackerConnection;
import com.pkpmcloud.fileserver.conn.Connection;
import com.pkpmcloud.fileserver.model.StorageNode;
import com.pkpmcloud.fileserver.testbase.GetTrackerConnection;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 作者：LiZW <br/>
 * 创建时间：2016/11/20 15:16 <br/>
 */
public class GetStorageNodeCommandTest {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(GetListStorageCommandTest.class);

    @Test
    public void test01() {
        Connection connection = GetTrackerConnection.getDefaultConnection();
        try {
            GetStorageNodeCommand command = new GetStorageNodeCommand();
            StorageNode storageNode = command.execute(connection);
            logger.info(storageNode.toString());

            command = new GetStorageNodeCommand("group1");
            storageNode = command.execute(connection);
            logger.info(storageNode.toString());
        } finally {
            connection.close();
        }
    }
}
