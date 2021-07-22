package com.hsicen.lib.advance;

/**
 * 作者：hsicen  7/20/21 17:55
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：位图数据结构
 * 1.通过数组下标来定位数据，访问效率非常高效
 * 2.每个数字用一个二进制位来表示，在数字范围不大的情况下，所需要的内存空间非常节省
 * 3.位图存储的是数据范围，所以数据范围越大，越占用内存
 * 4.使用布隆过滤器来解决位图数据范围过大占用空间的问题
 * <p>
 * 布隆过滤器：
 * 使用k个哈希函数对同一个数据进行hash，然后将得到的值在对应的位图位置为1，
 * 这样就可以利用位图的多位来判断一个数据是否存在(可能存在误判问题)
 */
public class BitMap {
    //一个char占用16 bit
    private final char[] bytes;
    //表示使用nbits位
    private final int nbits;

    public BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits / 16 + 1];
    }

    /*** 设置第k位为1*/
    public void set(int k) {
        if (k > nbits) return;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    /*** 重置第k位为0*/
    public void reset(int k) {
        if (k > nbits) return;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        bytes[byteIndex] &= ~(1 << bitIndex);
    }

    /*** 获取第k位是否为1*/
    public boolean get(int k) {
        if (k > nbits) return false;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }
}
