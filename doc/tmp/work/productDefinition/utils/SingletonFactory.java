package work.productDefinition.utils;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonFactory {

	public static final Logger LOG = Logger.getLogger(SingletonFactory.class.getCanonicalName());

	private static final Map<Class<?>, Object> INSTANCE_MAP = Collections.synchronizedMap(new HashMap<Class<?>, Object>());
	private static final Object[] EMPTY_ARGS = new Object[0];

	/**
	 * <pre>
	 * 鐢ㄦ鏂规硶鏈変竴涓墠锟�?
	 * 鍙傛暟绫诲繀椤绘湁鏄剧ず澹版槑鐨勬棤鍙傛瀯閫犲櫒鎴栨病鏈変换浣曟樉绀哄０鏄庣殑鏋勶拷?锟�?
	 * </pre>
	 */
	public static <T> T getInstance(Class<? extends T> clazz) {
		return getInstance(clazz, EMPTY_ARGS);
	}

	/**
	 * <pre>
	 * 鐢ㄦ鏂规硶鏈変袱涓墠锟�?
	 * 1. 鍙傛暟鏁扮粍鐨勯」閮戒笉鑳戒负null
	 * 2. 鍙傛暟鏁扮粍鐨勯」閮戒笉鑳芥槸锟�?锟斤拷鏁版嵁绫诲瀷
	 * </pre>
	 */
	public static <T> T getInstance(Class<? extends T> clazz, Object... args) {
		Object object = INSTANCE_MAP.get(clazz);
		if (object == null) {
			Class<?>[] parameterTypes = new Class<?>[args.length];
			for (int i = 0; i < args.length; i++) {
				parameterTypes[i] = args[i].getClass();
			}
			return getInstance(clazz, parameterTypes, args);
		}
		return clazz.cast(object);
	}

	/**
	 * <pre>
	 * 鐢ㄦ鏂规硶鏈変笁涓墠锟�?
	 * 1. 涓や釜鍙傛暟鏁扮粍鍚屾椂涓簄ull鎴栧悓鏃朵笉涓簄ull
	 * 2. 涓や釜鏁扮粍鐨勯暱搴﹀繀椤荤浉锟�?
	 * 3. parameterTypes濡傛灉涓嶇┖null, 鍒欏叾鍚勫厓绱犱笉鑳戒负null
	 * </pre>
	 */
	public static <T> T getInstance(Class<? extends T> clazz, Class<?>[] parameterTypes, Object[] args) {
		Object object = INSTANCE_MAP.get(clazz);
		if (object == null) { // 锟�?锟斤拷瀹炰緥,濡傛槸涓嶅瓨鍦ㄥ氨杩涜鍚屾浠ｇ爜锟�?
			synchronized (clazz) { // 瀵瑰叾杩涜锟�?闃叉涓や釜绾跨▼鍚屾椂杩涘叆鍚屾浠ｇ爜锟�?
				// 鍙岄噸锟�?锟斤拷,闈炲父閲嶈,濡傛灉涓や釜鍚屾椂璁块棶鐨勭嚎锟�?褰撶锟�?锟斤拷绋嬭闂畬鍚屾浠ｇ爜鍖哄悗,鐢熸垚锟�?锟斤拷瀹炰緥;褰撶浜屼釜宸茶繘鍏etInstance鏂规硶绛夊緟鐨勭嚎绋嬭繘鍏ュ悓姝ヤ唬鐮佸尯锟�?涔熶細浜х敓锟�?锟斤拷鏂扮殑瀹炰緥
				if (object == null) {
					try {
						if (parameterTypes != null && args != null) {
							if (parameterTypes.length == args.length) {
								Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
								constructor.setAccessible(true);
								T instance = clazz.cast(constructor.newInstance(args));
								INSTANCE_MAP.put(clazz, instance);
								return instance;
							} else {
								throw new IllegalArgumentException("鍙傛暟涓暟涓嶅尮閰�");
							}
						} else if (parameterTypes == null && args == null) {
							T instance = clazz.newInstance();
							INSTANCE_MAP.put(clazz, instance);
							return instance;
						} else {
							throw new IllegalArgumentException("涓や釜鍙傛暟鏁扮粍蹇呴』鍚屾椂涓簄ull鎴栧悓鏃朵笉涓簄ull");
						}
					} catch (Exception e) {
						LOG.log(Level.SEVERE, "鍒涘缓瀹炰緥澶辫触", e);
					}
				}
			}
		}
		return null;
	}
}