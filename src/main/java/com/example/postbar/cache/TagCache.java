package com.example.postbar.cache;


import com.example.postbar.dto.TagDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangxu
 * @title
 * @date 2019/9/9 12:57
 */

public class TagCache {
   public static List<TagDto> get(){
       List<TagDto> tagDtos=new ArrayList<>();
       TagDto front =new TagDto();
       front.setClassName("前端开发");
       front.setTags(Arrays.asList( "HTML/CSS","JavaScript","Vue.js","React.JS","Angular","Node.js","javascript","jQuery","小程序" ));
       tagDtos.add(front);

       TagDto backEnd =new TagDto();
       backEnd.setClassName("后端开发");
       backEnd.setTags(Arrays.asList( "Java","SpringBoot","Spring Cloud","SSM","Python","Django","Flask","Go","PHP","C","C++" ));
       tagDtos.add(backEnd);

       TagDto move =new TagDto();
       move.setClassName("移动开发");
       move.setTags(Arrays.asList( "Android","iOS","React native","Ionic"));
       tagDtos.add(move);

       TagDto dataBase =new TagDto();
       dataBase.setClassName("数据库");
       dataBase.setTags(Arrays.asList( "MySQL","Redis","oracle","MongoDB","sqlite","nosql"));
       tagDtos.add(dataBase);

       TagDto bigData =new TagDto();
       bigData.setClassName("大数据云计算");
       bigData.setTags(Arrays.asList( "大数据","Hadoop","Spark","Hbase","Flink","Storm","阿里云","Docker","Kubernetes"));
       tagDtos.add(bigData);

       return tagDtos;
   }
   //校验输入标签是否在标签库中
   public static String isExistTag(String tags){
       String[] splitag =StringUtils.split(tags,',');
       List<TagDto> tagDtoList=get();
       List<String> taglist=tagDtoList.stream().flatMap(tag-> tag.getTags().stream()).collect(Collectors.toList());
       String invalist=Arrays.stream(splitag).filter(t->!taglist.contains(t)).collect(Collectors.joining(","));
       return invalist;
   }
}
