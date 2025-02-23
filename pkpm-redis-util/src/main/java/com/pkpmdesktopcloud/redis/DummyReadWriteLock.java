package com.pkpmdesktopcloud.redis;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class DummyReadWriteLock implements ReadWriteLock {
    private Lock lock = new DummyLock();

    @Override
    public Lock readLock(){return lock;}
    @Override
    public Lock writeLock(){return lock;}

    static class DummyLock implements  Lock {
        @Override
        public void lock() {

        }
        @Override
        public void lockInterruptibly() throws InterruptedException{

        }
        @Override
        public boolean tryLock(){return true;}

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return true;
        }

        @Override
        public void unlock() {

        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }
}
