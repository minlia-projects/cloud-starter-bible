package com.minlia.module.bible.service;

import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.query.BibleQueryRequestBody;
import com.minlia.module.bible.repository.BibleRepository;
import com.minlia.module.data.jpa.abstraction.AbstractRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 * @author will
 */
@Service
public class BibleJpaServiceImpl implements BibleJpaService {

  @Override
  public AbstractRepository<Bible, Long> getJpaRepository() {
    return this.bibleRepository;
  }

  @Autowired
  private BibleRepository bibleRepository;

  @Override
  public Specification<Bible> getFindAllSpecification(BibleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<Bible> getCountSpecification(BibleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<Bible> getExistsSpecification(BibleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }


  public Specification<Bible> getConditions(BibleQueryRequestBody queryRequestBody) {
    return new Specification<Bible>() {
      @Nullable
      @Override
      public Predicate toPredicate(Root<Bible> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        Path<String> codeField = root.get("code");
        Path<String> labelField = root.get("label");
//        Path<String> appidField = root.get("appid");
//                Path<String> emailField = root.get("email");
//                Path<Integer> genderField=root.get("gender");
//                Path<Integer> typeField=root.get("type");
//        Path<Status> statusField=root.get("status");
//
//        Path<Merchant> merchantField=root.get("merchant");
        Path<Date> createdDateField = root.get("createdDate");

        List<Predicate> predicates = new ArrayList<Predicate>();

        //模糊搜素
        if (StringUtils.isNotBlank(queryRequestBody.getCode())) {
          predicates.add(cb.like(codeField, '%' + queryRequestBody.getCode() + '%'));
        }

        if (StringUtils.isNotBlank(queryRequestBody.getLabel())) {
          predicates.add(cb.like(labelField, '%' + queryRequestBody.getLabel() + '%'));
        }
//        if(StringUtils.isNotBlank(entity.getAppid())){
//          list.add(cb.like(appidField,'%'+entity.getAppid()+'%'));
//        }
//                if(StrUtil.isNotBlank(entity.getMobile())){
//                    list.add(cb.like(mobileField,'%'+entity.getMobile()+'%'));
//                }
//                if(StrUtil.isNotBlank(entity.getEmail())){
//                    list.add(cb.like(emailField,'%'+entity.getEmail()+'%'));
//                }
//
//                //性别
//                if(entity.getGender()!=null){
//                    list.add(cb.equal(genderField, entity.getGender()));
//                }
//        if(entity.getMerchant()!=null){
//          list.add(cb.equal(merchantField, entity.getMerchant()));
//        }
//                //类型
//                if(entity.getType()!=null){
//                    list.add(cb.equal(typeField, entity.getType()));
//                }
        //状态
//        if(entity.getStatus()!=null){
//          list.add(cb.equal(statusField, entity.getStatus()));
//        }
        //创建时间
//        if (StringUtils.isNotBlank(searchBody.getStartDate()) && StringUtils
//            .isNotBlank(searchBody.getEndDate())) {
//          Date start = DateUtil.parse(searchBody.getStartDate());
//          Date end = DateUtil.parse(searchBody.getEndDate());
//          predicates.add(cb.between(createdDateField, start, DateUtil.endOfDay(end)));
//        }

        Predicate[] arr = new Predicate[predicates.size()];
        cq.where(predicates.toArray(arr));
        return null;
      }
    };
  }

}
