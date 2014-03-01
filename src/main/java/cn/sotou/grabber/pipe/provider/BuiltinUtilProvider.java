package cn.sotou.grabber.pipe.provider;

import java.util.HashMap;
import java.util.Map;

import cn.sotou.grabber.pipe.exception.NoPipeUtilFoundException;
import cn.sotou.grabber.pipe.util.PipeUtil;

public class BuiltinUtilProvider implements IUtilProvider {

	private BuiltinUtilBuilder builtinUtilBuilder = new BuiltinUtilBuilder();

	private Map<String, PipeUtil> utilMap = new HashMap<String, PipeUtil>();

	public PipeUtil get(String name) {
		PipeUtil util = utilMap.get(name);
		if (util != null) {
			return util;
		} else {
			try {
				util = builtinUtilBuilder.build(name);
				if (util != null) {
					utilMap.put(name, util);
				}
				return util;
			} catch (NoPipeUtilFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
