package com.minlia.module.bible.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.minlia.module.bible.entity.Bible;
import com.minlia.module.data.batis.abstraction.AbstractMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author will
 */
public interface BibleDao extends AbstractMapper<Bible> {


}
