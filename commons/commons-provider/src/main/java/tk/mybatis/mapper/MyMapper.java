package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author jxlgcmh
 * @date 2019-12-30 17:01
 * @description
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
