package com.example.demo.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

public final class SingleKeyDynamicModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Date> {

    private final String tablePrefix;

    public SingleKeyDynamicModuloTableShardingAlgorithm(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<Date> shardingValue) {

        return tablePrefix + getStringDate(shardingValue.getValue());
    }

    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<Date> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(shardingValue.getValues().size());
        for (Date value : shardingValue.getValues()) {
            result.add(tablePrefix + getStringDate(value));
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<Date> shardingValue) {
        Collection<String> result = new LinkedHashSet<>();
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Range<Date> ranges = shardingValue.getValueRange();
        Date startTime = ranges.lowerEndpoint();
        Date endTime = ranges.upperEndpoint();
        // range.lowerEndpoint() = 2018-08-01
        // range.upperEndpoint() = 2018-10-01
        // 此处应该返回  tablePrefix+201808 , tablePrefix+201809,tablePrefix+201810,
        Calendar cal = Calendar.getInstance();
        while (startTime.getTime() <= endTime.getTime()) {
            result.add(tablePrefix + sdf.format(startTime));
            cal.setTime(startTime);//设置起时间
            cal.add(Calendar.MONTH, 1);
            startTime = cal.getTime();
        }
        return result;
    }

    public static String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static void main(String[] args) {
        System.out.println(getStringDate(new Date()));
    }
}
