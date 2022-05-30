package me.hsicen.javaguide

/******====== 容器 ======******/
/**
 * ## JCF
> Java 提供了非常多的容器，但这些容器并非杂乱无章、毫无联系的，而是构成了一个体系，叫作 JCF(Java Collections Framework)，类似 C++ 中的 STL(Standard Template Library):
> <img src="../Image/java13.png" alt="11-1.png" style="zoom:33%;" />
>
> 接口：`Collection`，`Map`
> 工具类：`Collections`，`Arrays`
>
> Collection实现：List，Set，Queue
> **List实现(元素可以重复)：**Vector(Stack)，ArrayList，LinkedList
> **Set实现(元素不能重复)：**HashSet，TreeSet，LinkedHashSet
> **Map实现(键值对存储)：**HashMap，TreeMap，LinkedHashMap，HashTable
>
> 尽管 Map 容器没有实现上图中 Collection 接口，但仍然属于 JCF 的一部分；上图中的 Collection 接口跟 Java Collections Framework 中的 Collections 并非同一个意思；Java Collections Framework 中的 Collections 表示容器的意思，而上图中的 Colleciton 只是一个接口。
>
> 在图中，Map 容器跟其他容器毫无关联，这是因为 Map 容器在用法上跟其他容器不同；Map 容器中存储键和值两部分数据，而其他容器只存储单一的值；除此之外，其他容器都支持遍历，而 Map 容器常用来通过“键”查找“值”，不支持直接的遍历操作；Map 容器没有实现 Iterable 接口，也就没有跟其他容器那样实现 Collection 接口。
>
> 我们将 JCF 中的容器分为 5 类：
>
> - List：ArrayList、LinkedList、Vector(废弃)
> - Stack：Stack(废弃)
> - Queue：ArrayDeque、LinkedList、PriorityQueue
> - Set：HashSet、LinkedHashSet、TreeSet
> - Map：HashMap、LinkedHashMap、TreeMap、HashTable(废弃)
>
> 这样分类，主要是结合两个方面因素来考虑：一是，从底层数据结构的归类来看，数组、链表、栈、队列、哈希表本应该划分为不同的类；二是，从用法上来看，同类容器往往基于相同的接口来创建，这样在使用过程中，可以互相替换。
> 尽管 Set 容器和 Map 容器都是基于哈希表来实现的，但在用法上无法互相替换，所以不能归为一类；同理 Stack、Queue 也几乎不会基于 List 接口创建，所以也单独做了分类。

## List

> List 可以存储重复元素，ArrayList、LinkedList、Vector 这三个类都实现了 List 接口，并且继承了 AbstractList 抽象类；仔细观察 JCF 层次图，其他类别的容器也实现一个接口并继承一个抽象类；接口存在方便开发中替换不同的实现类，抽象类则可以实现代码复用，ArrayList、LinkedList、Vector 中的一些方法的代码实现是相同，为了避免重复编写，我们就得找个地方存放这些公共的代码，那就设计了一个抽象类。

### ArrayList

> 数据结构：可动态扩容的数组
> 优点：查找数据快(二分查找)，根据下标随机访问
> 缺点：需要一段连续的存储空间，新增和删除数据需要移动位置，O(n)时间复杂度
>
> 默认大小为10，初始化对象时创建数组，非线程安全
> 扩容 1.5 倍，Arrays.copyOf() 迁移数据
> 最大元素个数为 Int.MaxValue = 1<<31-1

### LinkedList

> 数据结构：双向链表，支持双向遍历
> 优点：有利于插入和删除操作，不需要申请连续的存储空间，插入的数据是有序的(默认使用尾插法)
> 缺点：不利于元素的查找，O(n)时间复杂度
>
> 尾插法新增结点，非线程安全
> 结点查找以 List 大小的 1/2 为分界点，大于分界点的下标从尾结点开始查找，反之从头结点开始查找

### Vector

> 数据结构：可动态扩容的数组
> 优点：查找数据快(二分查找)，根据下标随机访问
> 缺点：需要一段连续的存储空间，新增和删除数据需要移动位置，O(n)时间复杂度
>
> 默认大小为10，初始化对象时创建数组，线程安全的(Synchronized)
> 扩容时可以手动指定每次扩容的大小，默认扩容为当前数组大小的1倍，Arrays.copyOf() 迁移数据
> 最大元素个数为 Int.MaxValue = 1<<31-1
>
> Vector 是线程安全的 ArrayList，早在 JDK1.0 就存在了；它是模仿了 C++ STL 中 的 Vector，Java 中的很多特性都来自于 C++；JDK1.0 中还没有JCF，只有少数的几个容器，比如 Vector、Stack、HashTable，而且都是线程安全的；JDK1.2 设计了 JCF，定义了一套完善的容器框架，从功能上完全可以替代 JDK1.0 中引入的Vector、Stack、HashTable；但为了兼容，Java 并没有将 Vector、Stack、HashTable 从 JDK 中移除，而是标记为过时，不再推荐使用。
>
> 为了更符合程序员的开发习惯，JCF 将线程安全容器和非线程安全容器分开来设计；在非多线程环境下，我们使用没有加锁的容器，性能更高；对于多线程环境，我们可以使用 Collections 工具类提供的 sychronizedList() 方法，将非线程安全的 List，转换为线程安全的 SynchronizedList，或者使用 JUC(java.util.concurrent) 提供的 CopyOnWriteArrayList。

## Stack

> Stack 也是 JDK1.0 的产物，继承自 Vector，也是线程安全的，在项目中不再推荐使用；可以使用 Deque 双端队列来模拟栈；双端队列支持在一端存入数据、取出数据，支持先进后出的访问模式。

## Queue

> JCF 中的队列分为 双端队列(Deque) 和 优先级队列(PriorityQueue)；普通队列只能队尾添加元素，队首获取元素；双端队列的首尾两端，均可添加和获取元素。
>
> 双端队列有两种实现方式：基于数组来实现和基于链表来实现，在 JCF 中，ArrayDeque 就是基于数组实现的队列，LinkedList 就是基于链表实现的队列，LinkedList 类在实现时，实现了 Deque 接口：
>
> ```java
> public class LinkedList<E>
>     extends AbstractSequentialList<E>
>     implements List<E>, Deque<E>, Cloneable, Serializable {
>   //...省略代码实现...
> }
> ```
>
> 优先级队列底层依赖堆这种数据结构来实现，对应 JCF 容器 PriorityQueue；默认情况下，优先级队列基于小顶堆来实现的，最先出队列的为当前队列中的最小值；也可以通过使用 Comparator 接口的匿名类对象，改变优先级队列的实现方式，改为基于大顶堆来实现：
>
> ```java
> // 默认为小顶堆实现的优先级队列
> PriorityQueue<Integer> pq = new PriorityQueue<>();
> pq.add(2);
> pq.add(3);
> pq.add(1);
> while (!pq.isEmpty()) {
>   System.out.println(pq.poll()); //输出1、2、3
> }
>
> // 改为大顶堆实现的优先级队列
> PriorityQueue<Integer> rpq = new PriorityQueue<>(new Comparator<Integer>() {
>   @Override
>   public int compare(Integer o1, Integer o2) {
>     return o2 - o1;
>   }
> });
> rpq.add(2);
> rpq.add(3);
> rpq.add(1);
> while (!rpq.isEmpty()) {
>   System.out.println(rpq.poll()); //输出3、2、1
> }
> ```
>
> 堆的构建过程，需要比较节点中数据的大小，所以，添加到优先级队列中的元素，需要能够比较大小；比较大小的方法有两种：基于Comparable接口 和 基于Comparator接口:
>
> ```java
> // 不管是使用 Comparable 的 o1.compareTo(o2) 方法
> // 还是使用 Comparator 的 compare(o1, o2) 方法
> // o1<o2 时都返回负数，o1==o2 时都返回0，o1>o2 时都返回正数
> public interface Comparable<T> {
>     public int compareTo(T o);
> }
>
> public interface Comparator<T> {
>     int compare(T o1, T o2);
> }
> ```

## Set

> Set 容器跟 List 容器都可以用来存储一组数据，不同的地方在于，List 容器中有下标的概念，不同下标对应的位置可以存储相同的数据，而 Set 容器没有下标的概念，不允许存储相同的数据。
>
> Set 容器包括 HashSet、LinkedHashSet、TreeSet；从代码实现上来说，这三个类底层分别是依赖 HashMap、LinkedHashMap、TreeMap 这三个类实现的；例如往 HashSet 中存储对象 obj，底层实现是将 obj 值作为键，一个空的 Object 对象做为值，一并存储到 HashMap 中；所以 Set 容器相当于是 Map 容器的封装。
>

### HashSet

> 底层利用 HashMap 实现，HashSet 元素集合就是 HashMap 的 key 集合，所以 HashSet 中元素不会重复，HashMap 的 value 为 Object 对象；HashSet 排序假象是因为 HashSet 内部是由 HashMap 实现，计算 hashCode 的时候造成了排序的耦合现象
> 不保证插入顺序

### LinkedHashSet

> 继承自 HashSet，初始大小16，填充因子0.75
> 按照插入顺序存储数据

### TreeSet

> 利用 TreeMap 实现，TreeSet 元素集合就是 TreeMap 的 key 集合
> 集合内的元素会根据 Comparable 比较器排序，可以在实例化 TreeSet 时实现 Comparable 接口，或者存储的对象实现 Comparable 接口

## Map

> Map 容器包括 HashMap、LinkedHashMap 和 TreeMap，Map 容器不支持存储重复的键。

### HashMap

> 1.7版本：由数组+链表实现；数组是索引,链表是存储的具体的内容，使用链表法解决冲突，关键是 hash 算法要好，添加数据是头插法，在进行 put 操作时才会进行数组的创建
> 1.8版本：是由数组+链表/树/红黑树实现；添加数据是尾插法，在进行 put 操作时才会进行数组的创建
>
> HashMap 数组的容量始终要是 2 的幂次方倍(tableSizeFor()方法)，初始容量16，最大容量 1<<30，填装因子0.75，扩容的新数组大小为原数组的 2 倍
> 树化条件：当链表的长度大于`8`的时候会树化，大于`64`的时候会进行红黑树化
>
> HashMap 是线程不安全的，不能保证插入数据的顺序；根据 `key` 的 hashcode 来确定放在数组那个位置的，有自己的 hash 函数，要重写 key 对象的 hashcode 方法
>
> `红黑树`是平衡二叉树的一种，保证二叉树中任意一个结点的左右子树的高度相差不能大于一，在插入数据时要进行左旋或者右旋调整树的结构来保持平衡二叉树的性质；红色节点存储数据，黑色节点不存储数据
>
> - 根节点是黑色的
> - 每个叶子节点都是黑色的空节点，也就是说，叶子节点不存储数据
> - 任何相邻的节点都不能同时为红色，也就是说，红色节点是被黑色节点隔开的
> - 每个节点，从该节点到达其可达叶子节点的所有路径，都包含相同数目的黑色节点
>
> 始终保持树的高度为logn

### LinkedHashMap

> 利用 HashMap 实现，保证数据按照插入的顺序存储，LRUCache 内部数据结构的使用

### TreeMap

> TreeMap 是基于红黑树来实现的，TreeMap 基于键值对的键来构建红黑树，值作为卫星数据，附属存储在红黑树的节点中。
>
> TreeMap 底层依赖红黑树，红黑树的构建也需要比较元素的大小，元素会根据 Key 进行排序，跟 PriorityQueue 类似，在使用 TreeMap 时，要么键值对中的键实现 Comparable 接口，要么在创建 TreeMap 时传入 Comparator 接口的匿名类对象。
>
> TreeMap 直接实现的接口是 SortedMap，而非 Map；这是因为 TreeMap 底层依赖红黑树来实现，其中序遍历的结果是有序的；TreeMap 可以提供更加丰富的功能，比如查看最大键值、最小键值、大于某个值的键值、有序输出所有的键值等等，这些操作都定义在 SortedMap 接口中。

### HashTable

> 利用数组+链表实现，初始化容量 11，填装因子 0.75，扩容的新数组大小为原数组的 2倍并+1，然后再次 hash 数组中的元素在数组中的下标位置
> 先扩容再新增数据，初始化时就创建数组
> 通过添加 Synchronized 关键字实现线程安全
 */