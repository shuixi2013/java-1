package by.lang;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

public class ArrayUtilsTest {
	public static void main(String[] args) {
		int[] array1 = { 1, 2 };
		// 追加元素到数组尾部
		array1 = ArrayUtils.add(array1, 3); // => [1, 2, 3]
		// 删除指定位置的元素
		int[] array2 = { 1, 2, 3 };
		array2 = ArrayUtils.remove(array2, 2); // => [1, 2]
		// 截取部分元素
		int[] array3 = { 1, 2, 3, 4 };
		array3 = ArrayUtils.subarray(array3, 1, 3); // => [2, 3]
		// 数组拷贝
		String[] array4 = { "aaa", "bbb", "ccc" };
		String[] copied = (String[]) ArrayUtils.clone(array4); // => {"aaa","bbb", "ccc"}
		// 判断是否包含某元素
		String[] array5 = { "aaa", "bbb", "ccc", "bbb" };
		boolean result1 = ArrayUtils.contains(array5, "bbb"); // => true
		System.out.println(result1);// true
		// 判断某元素在数组中出现的位置（从前往后，没有返回-1）
		int result2 = ArrayUtils.indexOf(array5, "bbb"); // => 1
		// 判断某元素在数组中出现的位置（从后往前，没有返回-1）
		int result3 = ArrayUtils.lastIndexOf(array5, "bbb"); // => 3
		// 数组转Map
		Map<Object, Object> map = ArrayUtils.toMap(new String[][] { { "key1", "value1" }, { "key2", "value2" } });
		System.out.println(map.get("key1"));// "value1"
		System.out.println(map.get("key2"));// "value2"
		// 判断数组是否为空
		Object[] array61 = new Object[0];
		Object[] array62 = null;
		Object[] array63 = new Object[] { "aaa" };
		System.out.println(ArrayUtils.isEmpty(array61));// true
		System.out.println(ArrayUtils.isEmpty(array62));// true
		System.out.println(ArrayUtils.isNotEmpty(array63));// true
		// 判断数组长度是否相等
		Object[] array71 = new Object[] { "aa", "bb", "cc" };
		Object[] array72 = new Object[] { "dd", "ee", "ff" };
		System.out.println(ArrayUtils.isSameLength(array71, array72));// true
		// 判断数组元素内容是否相等
		Object[] array81 = new Object[] { "aa", "bb", "cc" };
		Object[] array82 = new Object[] { "aa", "bb", "cc" };
		System.out.println(ArrayUtils.isEquals(array81, array82));
		// Integer[] 转化为 int[]
		Integer[] array9 = new Integer[] { 1, 2 };
		int[] result = ArrayUtils.toPrimitive(array9);
		// int[] 转化为 Integer[]
		int[] array10 = new int[] { 1, 2 };
		Integer[] result10 = ArrayUtils.toObject(array10);
		// 输出数组,第二参数为数组为空时代替输出
		System.out.println(ArrayUtils.toString(array10, "array is null"));
		// 克隆新数组,注意此拷贝为深拷贝
		int[] nums3 = ArrayUtils.clone(array10);
		System.out.println(ArrayUtils.subarray(nums3, 1, 2));
		int[] nums1 = nums3;
		int[] nums2 = nums3;
		// 判断两个数组类型是否相等,注意int和Integer比较时不相等
		System.out.println(ArrayUtils.isSameType(nums1, nums2));
		ArrayUtils.reverse(nums1);
		System.out.println(ArrayUtils.indexOf(nums1, 5));
		// 查找数组元素最后出现位置
		System.out.println(ArrayUtils.lastIndexOf(nums1, 4));
		// 查找元素是否存在数组中
		ArrayUtils.contains(nums1, 2);
		// 将基本数组类型转为包装类型
		Integer[] nums4 = ArrayUtils.toObject(nums1);
		// 判断是否为空,length=0或null都属于
		ArrayUtils.isEmpty(nums1);
		ArrayUtils.addAll(nums1, nums2);
		// 增加元素,在下标5中插入10,注意此处返回是新数组
		ArrayUtils.add(nums1, 5, 1111);
		// 删除指定位置元素,注意返回新数组,删除元素后面的元素会前移,保持数组有序
		ArrayUtils.remove(nums1, 5);
		// 删除数组中值为10的元素,以值计算不以下标
		ArrayUtils.removeElement(nums1, 10);
	}
}
