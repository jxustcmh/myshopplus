package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author jxlgcmh
 * @date 2019-09-14 17:42
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
