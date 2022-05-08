package com.hsicen.letcode.custom/** * 作者：hsicen  7/30/21 09:21 * 邮箱：codinghuang@163.com * 作用： * 描述：索引构建 * * 开发本质：对数据的存储和计算 *  存储->数据结构->增删改查->索引 *  计算->算法 * * 索引设计思路: *  功能性需求： *   - 数据是格式化数据还是非格式化数据 *   - 数据是静态数据还是动态数据 *   - 索引存储在内存上还是磁盘上 *   - 单值查找还是区间查找 *   - 单关键词查找还是多关键词组合查找 *  非功能性需求： *   - 索引对内存空间的消耗不能过大 *   - 索引的维护成本 *  索引构架的数据结构： *   - 散列表 *   - 红黑树 *   - B+树 *   - 跳表 *   - 跳表和布隆过滤器 *   - 数组(静态数据) */