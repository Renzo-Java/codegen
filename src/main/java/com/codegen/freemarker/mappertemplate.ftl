<#include "common.ftl">
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${_daofullname}">
	
    <sql id="allFields">
    	<#list obj.props as prop> 
		<#if prop.columnName != prop.name>
		${prop.columnName} as ${prop.name}<#if prop_has_next>,</#if>
		<#else>
		${prop.columnName}<#if prop_has_next>,</#if>
		</#if>
		</#list>
    </sql>
    
	<sql id="insertFields">
    	<#list obj.props as prop> 
		${prop.columnName}<#if prop_has_next>,</#if>
		</#list>
    </sql>
	
	<insert id="create" parameterType="${_cfullname}" useGeneratedKeys="true" keyProperty="${obj.primaryKey}">
		insert into ${obj.tableName} (<include refid="insertFields"/>)
		values (
		<#list obj.props as prop> 
			#${_brack}${prop.name}}<#if prop_has_next>,</#if>
		</#list>
		)
	</insert>
	
	<insert id="createInBatch" parameterType="${_cfullname}">
		insert into ${obj.tableName} (<include refid="insertFields"/>) values 
		<foreach collection="collection" item="item" index="index" separator=",">
			(
			<#list obj.props as prop> 
				#${_brack}item.${prop.name}}<#if prop_has_next>,</#if>
			</#list>
			)
		</foreach>
	</insert>   		


	<sql id="whereClause">
		<#list obj.props as prop>
		<#if obj.primaryKey != prop.name>
		<#if prop.type = "String">
		<if test='${prop.name} != null and ${prop.name} != ""'>
			<#else>
			<if test="${prop.name} != null">
				</#if>
					and ${prop.columnName} = #${_brack}${prop.name}}
			</if>
			<#if prop.name = "createdAt" && prop.type != "String">
			<if test="createdAtGte != null">
				    and created_at &gt;= #${_brack}createdAtGte}
			</if>
			<if test="createdAtLte != null">
				    and created_at &lt;= #${_brack}createdAtLte}
			</if>
			</#if>
			</#if>
			</#list>
	</sql>

	<#if _haveBizkeys = '1'>
	
	<sql id="bizKeysWhereClause">
	<#list obj.bizKeys as biz> 
		<#if obj.primaryKey != biz.name>
		<#if biz.type = "String">
		<if test='${biz.name} != null and ${biz.name} != ""'>
		<#else>
		<if test="${biz.name} != null">
		</#if>
			and ${biz.columnName} = #${_brack}${biz.name}}
		</if>
		</#if>
	</#list>
	</sql>
	</#if>
    
	<select id="getBy${obj.primaryKey?cap_first}" resultType="${_cfullname}" parameterType="${obj.pkJavaType}" >
		select <include refid="allFields"/> 
		  from ${obj.tableName} 
		 where ${obj.jdbcPK} = #${_brack}${obj.primaryKey}} 
	</select>

	<select id="getByMap" resultType="${_cfullname}" parameterType="Map" >
		select <include refid="allFields"/>
		from ${obj.tableName}
		<where>
			<include refid="whereClause"/>
		</where>
	</select>
	
	<select id="getBy${obj.primaryKey?cap_first}s" resultType="${_cfullname}">
		select <include refid="allFields"/> from ${obj.tableName} 
		where ${obj.jdbcPK} in
		<foreach collection="collection" item="item" open="(" close=")" separator=",">
			${r"#{item}"}
		</foreach>
	</select>

	<#if _haveBizkeys = '1'>
	<select id="getByBizKeys" resultType="${_cfullname}" parameterType="Map" >
		select <include refid="allFields"/> 
		  from ${obj.tableName} 
		 <where>
		    <include refid="bizKeysWhereClause"/>
		</where>
	</select>
	</#if>
		
	<select id="getAll" resultType="${_cfullname}">
		select <include refid="allFields"/> 
		from ${obj.tableName} 
	</select>

	<select id="count" resultType="long">
		select count(*) from ${obj.tableName} 
	</select>
	
	<select id="getCountByWhere" parameterType="Map" resultType="Long">
		select count(*) from ${obj.tableName} 
		<where>
		    <include refid="whereClause"/>
		</where>
	</select>

	<select id="getListByMap" parameterType="Map" resultType="${_cfullname}">
		select <include refid="allFields"/>
		from ${obj.tableName} 
		<where>
		    <include refid="whereClause"/>
		</where>				
		<if test="orderBy != null">
			ORDER BY ${r"${orderBy}"}
			<if test="orderByDesc != null and orderByDesc == 'true'">
				desc
			</if>
		</if>
	</select>
	
	<sql id="setComn">		
		<#list obj.props as prop> 
		<#if obj.primaryKey != prop.name>
		<#if prop.jdbcType = 'JSON'>
		<if test="${prop.name} != null">
			<choose>
				<when test='${prop.name} == ""'>
					${prop.columnName} = null<#if prop_has_next>,</#if>
				</when>
		    	<otherwise>
					${prop.columnName} = #${_brack}${prop.name}}<#if prop_has_next>,</#if>
		    	</otherwise>
	    	</choose>
    	</if>
		<#else>
		<if test="${prop.name} != null">
			${prop.columnName} = #${_brack}${prop.name}}<#if prop_has_next>,</#if>
		</if>
		</#if>
		</#if>
		</#list>
	</sql>
	
	<update id="updateBy${obj.primaryKey?cap_first}" parameterType="${_cfullname}">
		 update ${obj.tableName} 
		    <set>
		    	<include refid="setComn"/>
			</set>
		  where ${obj.jdbcPK} = #${_brack}${obj.primaryKey}}
	</update>
	
	<update id="updateByMap" parameterType="Map">
		 update ${obj.tableName} 
		    <set>
		    	<include refid="setComn"/>
			</set>
		  where ${obj.jdbcPK} = #${_brack}${obj.primaryKey}}
	</update>
	
	<#if _haveBizkeys = '1'>	
	<update id="updateByBizKeys" parameterType="${_cfullname}">
		 update ${obj.tableName} 
		    <set>
		    	<include refid="setComn"/>
			</set>
		 <where>
		    <include refid="bizKeysWhereClause"/>
		</where>
	</update>		
	</#if>	
	
	<delete id="deleteBy${obj.primaryKey?cap_first}">
		delete from ${obj.tableName} where ${obj.jdbcPK} = #${_brack}${obj.primaryKey}}
	</delete>
	
	<delete id="multiDelete" parameterType="List">
		delete from ${obj.tableName}
		where ${obj.jdbcPK} in
		<foreach collection="list" item="item" open="(" close=")" separator=",">
			${r"#{item}"}
		</foreach>
	</delete>
	
	<delete id="deleteByWhere" parameterType="Map">
		delete from ${obj.tableName} 
		<where>
		    <include refid="whereClause"/>
		</where>
	</delete>	
	
	<#if _haveBizkeys = '1'>	
	<delete id="deleteByBizKeys" parameterType="Map">
		delete from ${obj.tableName} 
		<where>
		    <include refid="bizKeysWhereClause"/>
		</where>
	</delete>		
	</#if>
</mapper>    
