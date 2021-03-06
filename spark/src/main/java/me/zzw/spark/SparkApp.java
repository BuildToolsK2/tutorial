/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.zzw.spark;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

/**
 *
 * @author infosea
 */
 class GetLength implements Function<String, Integer> {
  public Integer call(String s) { return s.length(); }
}
class Sum implements Function2<Integer, Integer, Integer> {
  public Integer call(Integer a, Integer b) { return a + b; }
}
public class SparkApp {
   


    public static void main(String[] args){
        String appName="local";
        String master ="master";
        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
JavaSparkContext sc = new JavaSparkContext(conf);

//List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
//JavaRDD<Integer> distData = sc.parallelize(data);
//JavaRDD<String> distFile = sc.textFile("data.txt");

//JavaRDD<String> lines = sc.textFile("data.txt");
//JavaRDD<Integer> lineLengths = lines.map(s -> s.length());
//int totalLength = lineLengths.reduce((a, b) -> a + b);
//lineLengths.persist(StorageLevel.MEMORY_ONLY());

JavaRDD<String> lines = sc.textFile("data.txt");
JavaRDD<Integer> lineLengths = lines.map(new GetLength());
int totalLength = lineLengths.reduce(new Sum());
    }
}
