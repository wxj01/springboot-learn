package com.wxj.springboot.convert;

import com.wxj.springboot.domain.entity.User;
import com.wxj.springboot.domain.entity.UserEnum;
import com.wxj.springboot.domain.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserCoverBasic.java
 * @Description
 *
 * 当字段类型不一致时，以下的类型之间是 mapstruct 自动进行类型转换的:
 *
 * 1、基本类型及其他们对应的包装类型。
 * 此时 mapstruct 会自动进行拆装箱。不需要人为的处理
 * 2、基本类型的包装类型和string类型之间
 * 除此之外的类型转换我们可以通过定义表达式来进行指定转换
 *
 * @createTime 2022年07月02日 20:55:00
 */
@Mapper(componentModel = "spring")
public interface UserCoverBasic {

    UserCoverBasic INSTANCE = Mappers.getMapper(UserCoverBasic.class);

    /**
     * 属性数量类型相同，可以利用BeanUtils 也可以实现类似的效果
     *
     * @param user
     * @return
     */
    UserVO toConvertVo(User user);

    /**
     * 属性数量类型相同，可以利用BeanUtils 也可以实现类似的效果
     *
     * @param userVO
     * @return
     */
    User toConvertEntity(UserVO userVO);

    /**
     * 字段数量类型相同,数量少：仅能让多的转换成少的，故没有toConvertEntity2
     *
     * @param user
     * @return
     */
    UserTwoVO toConvertVo2(User user);

    /**
     * 字段数量相同，其中两个字段的类型不同
     *
     * @param user
     * @return
     */
    @Mappings({
            @Mapping(target = "createTime", expression = "java(com.wxj.springboot.utils.DateTransform.strToDate(user.getCreateTime()))")
    })
    UserThreeVO toConvertVo3(User user);

    /**
     * 将vo3转成user
     * @param userThreeVO
     * @return
     */
    User toConvertEntity3(UserThreeVO userThreeVO);

    /**
     * vo4 与 user 中有两个字段的名称不一样
     * @param user
     * @return
     */
    @Mappings({
            @Mapping(target = "userId",source = "id"),
            @Mapping(target = "userName",source = "name")
    })
    UserFourVO toConvertVo4(User user);

    /**
     * vo4 转成 user
     * @param userFourVO
     * @return
     */
    User toConvertEntity4(UserFourVO userFourVO);

    /**
     * vo 和 user 的 類型不一樣
     * @param user
     * @return
     */
    @Mapping(target = "type",source = "userTypeEnum")
    User5VO toConvertVo5(UserEnum user);

    /**
     * vo5 轉成 UserEnum
     * @param user5VO
     * @return
     */
    UserEnum toConvertUserEnum(User5VO user5VO);
}
