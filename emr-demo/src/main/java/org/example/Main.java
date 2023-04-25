package org.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class Main {
    public static void main(String[] args) {
        String logFile = "s3a://shai-emr/input/README.md";

        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("Simple Application")
                .getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();
        long numAs = logData.filter((FilterFunction<String>) s -> s.contains("a")).count();
        long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();
        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
        spark.stop();
    }
}