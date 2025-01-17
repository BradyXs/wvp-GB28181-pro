package com.genersoft.iot.vmp.gb28181.dao;

import com.genersoft.iot.vmp.gb28181.bean.Device;
import com.genersoft.iot.vmp.gb28181.bean.DeviceChannel;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用于存储设备信息
 */
@Mapper
@Repository
public interface DeviceMapper {

    @Select("SELECT " +
            "id, " +
            "device_id, " +
            "coalesce(custom_name, name) as name, " +
            "password, " +
            "manufacturer, " +
            "model, " +
            "firmware, " +
            "transport," +
            "stream_mode," +
            "ip," +
            "sdp_ip," +
            "local_ip," +
            "port," +
            "host_address," +
            "expires," +
            "register_time," +
            "keepalive_time," +
            "create_time," +
            "update_time," +
            "charset," +
            "subscribe_cycle_for_catalog," +
            "subscribe_cycle_for_mobile_position," +
            "mobile_position_submission_interval," +
            "subscribe_cycle_for_alarm," +
            "ssrc_check," +
            "as_message_channel," +
            "geo_coord_sys," +
            "on_line," +
            "media_server_id," +
            "broadcast_push_after_ack," +
            "(SELECT count(0) FROM wvp_device_channel dc WHERE dc.device_db_id= de.id) as channel_count "+
            " FROM wvp_device de WHERE de.device_id = #{deviceId}")
    Device getDeviceByDeviceId(String deviceId);

    @Insert("INSERT INTO wvp_device (" +
                "device_id, " +
                "name, " +
                "manufacturer, " +
                "model, " +
                "firmware, " +
                "transport," +
                "stream_mode," +
                "ip," +
                "sdp_ip," +
                "local_ip," +
                "port," +
                "host_address," +
                "expires," +
                "register_time," +
                "keepalive_time," +
                "keepalive_interval_time," +
                "create_time," +
                "update_time," +
                "charset," +
                "subscribe_cycle_for_catalog," +
                "subscribe_cycle_for_mobile_position,"+
                "mobile_position_submission_interval,"+
                "subscribe_cycle_for_alarm,"+
                "ssrc_check,"+
                "as_message_channel,"+
                "broadcast_push_after_ack,"+
                "geo_coord_sys,"+
                "on_line"+
            ") VALUES (" +
                "#{deviceId}," +
                "#{name}," +
                "#{manufacturer}," +
                "#{model}," +
                "#{firmware}," +
                "#{transport}," +
                "#{streamMode}," +
                "#{ip}," +
                "#{sdpIp}," +
                "#{localIp}," +
                "#{port}," +
                "#{hostAddress}," +
                "#{expires}," +
                "#{registerTime}," +
                "#{keepaliveTime}," +
                "#{keepaliveIntervalTime}," +
                "#{createTime}," +
                "#{updateTime}," +
                "#{charset}," +
                "#{subscribeCycleForCatalog}," +
                "#{subscribeCycleForMobilePosition}," +
                "#{mobilePositionSubmissionInterval}," +
                "#{subscribeCycleForAlarm}," +
                "#{ssrcCheck}," +
                "#{asMessageChannel}," +
                "#{broadcastPushAfterAck}," +
                "#{geoCoordSys}," +
                "#{onLine}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(Device device);

    @Update(value = {" <script>" +
                "UPDATE wvp_device " +
                "SET update_time=#{updateTime}" +
                "<if test=\"name != null\">, name=#{name}</if>" +
                "<if test=\"manufacturer != null\">, manufacturer=#{manufacturer}</if>" +
                "<if test=\"model != null\">, model=#{model}</if>" +
                "<if test=\"firmware != null\">, firmware=#{firmware}</if>" +
                "<if test=\"transport != null\">, transport=#{transport}</if>" +
                "<if test=\"ip != null\">, ip=#{ip}</if>" +
                "<if test=\"localIp != null\">, local_ip=#{localIp}</if>" +
                "<if test=\"port != null\">, port=#{port}</if>" +
                "<if test=\"hostAddress != null\">, host_address=#{hostAddress}</if>" +
                "<if test=\"onLine != null\">, on_line=#{onLine}</if>" +
                "<if test=\"registerTime != null\">, register_time=#{registerTime}</if>" +
                "<if test=\"keepaliveTime != null\">, keepalive_time=#{keepaliveTime}</if>" +
                "<if test=\"keepaliveIntervalTime != null\">, keepalive_interval_time=#{keepaliveIntervalTime}</if>" +
                "<if test=\"expires != null\">, expires=#{expires}</if>" +
                "WHERE device_id=#{deviceId}"+
            " </script>"})
    int update(Device device);

    @Select(
            " <script>" +
            "SELECT " +
            "id, " +
            "device_id, " +
            "coalesce(custom_name, name) as name, " +
            "password, " +
            "manufacturer, " +
            "model, " +
            "firmware, " +
            "transport," +
            "stream_mode," +
            "ip,"+
            "sdp_ip,"+
            "local_ip,"+
            "port,"+
            "host_address,"+
            "expires,"+
            "register_time,"+
            "keepalive_time,"+
            "create_time,"+
            "update_time,"+
            "charset,"+
            "subscribe_cycle_for_catalog,"+
            "subscribe_cycle_for_mobile_position,"+
            "mobile_position_submission_interval,"+
            "subscribe_cycle_for_alarm,"+
            "ssrc_check,"+
            "as_message_channel,"+
            "broadcast_push_after_ack,"+
            "geo_coord_sys,"+
            "on_line,"+
            "media_server_id,"+
            "(SELECT count(0) FROM wvp_device_channel dc WHERE dc.device_db_id= de.id) as channel_count " +
            "FROM wvp_device de" +
            "<if test=\"onLine != null\"> where de.on_line=${onLine}</if>"+
            " order by de.create_time desc "+
            " </script>"
    )
    List<Device> getDevices(Boolean onLine);

    @Delete("DELETE FROM wvp_device WHERE device_id=#{deviceId}")
    int del(String deviceId);

    @Select("SELECT " +
            "id, " +
            "device_id, " +
            "coalesce(custom_name, name) as name, " +
            "password, " +
            "manufacturer, " +
            "model, " +
            "firmware, " +
            "transport," +
            "stream_mode," +
            "ip," +
            "sdp_ip,"+
            "local_ip,"+
            "port,"+
            "host_address,"+
            "expires,"+
            "register_time,"+
            "keepalive_time,"+
            "create_time,"+
            "update_time,"+
            "charset,"+
            "subscribe_cycle_for_catalog,"+
            "subscribe_cycle_for_mobile_position,"+
            "mobile_position_submission_interval,"+
            "subscribe_cycle_for_alarm,"+
            "ssrc_check,"+
            "as_message_channel,"+
            "broadcast_push_after_ack,"+
            "geo_coord_sys,"+
            "on_line"+
            " FROM wvp_device WHERE on_line = true")
    List<Device> getOnlineDevices();

    @Select("SELECT " +
            "id,"+
            "device_id,"+
            "coalesce(custom_name,name)as name,"+
            "password,"+
            "manufacturer,"+
            "model,"+
            "firmware,"+
            "transport,"+
            "stream_mode,"+
            "ip,"+
            "sdp_ip,"+
            "local_ip,"+
            "port,"+
            "host_address,"+
            "expires,"+
            "register_time,"+
            "keepalive_time,"+
            "create_time,"+
            "update_time,"+
            "charset,"+
            "subscribe_cycle_for_catalog,"+
            "subscribe_cycle_for_mobile_position,"+
            "mobile_position_submission_interval,"+
            "subscribe_cycle_for_alarm,"+
            "ssrc_check,"+
            "as_message_channel,"+
            "broadcast_push_after_ack,"+
            "geo_coord_sys,"+
            "on_line"+
            " FROM wvp_device WHERE ip = #{host} AND port=#{port}")
    Device getDeviceByHostAndPort(@Param("host") String host, @Param("port") int port);

    @Update(value = {" <script>" +
            "UPDATE wvp_device " +
            "SET update_time=#{updateTime}" +
            "<if test=\"name != null\">, custom_name=#{name}</if>" +
            "<if test=\"password != null\">, password=#{password}</if>" +
            "<if test=\"streamMode != null\">, stream_mode=#{streamMode}</if>" +
            "<if test=\"ip != null\">, ip=#{ip}</if>" +
            "<if test=\"sdpIp != null\">, sdp_ip=#{sdpIp}</if>" +
            "<if test=\"port != null\">, port=#{port}</if>" +
            "<if test=\"charset != null\">, charset=#{charset}</if>" +
            "<if test=\"subscribeCycleForCatalog != null\">, subscribe_cycle_for_catalog=#{subscribeCycleForCatalog}</if>" +
            "<if test=\"subscribeCycleForMobilePosition != null\">, subscribe_cycle_for_mobile_position=#{subscribeCycleForMobilePosition}</if>" +
            "<if test=\"mobilePositionSubmissionInterval != null\">, mobile_position_submission_interval=#{mobilePositionSubmissionInterval}</if>" +
            "<if test=\"subscribeCycleForAlarm != null\">, subscribe_cycle_for_alarm=#{subscribeCycleForAlarm}</if>" +
            "<if test=\"ssrcCheck != null\">, ssrc_check=#{ssrcCheck}</if>" +
            "<if test=\"asMessageChannel != null\">, as_message_channel=#{asMessageChannel}</if>" +
            "<if test=\"broadcastPushAfterAck != null\">, broadcast_push_after_ack=#{broadcastPushAfterAck}</if>" +
            "<if test=\"geoCoordSys != null\">, geo_coord_sys=#{geoCoordSys}</if>" +
            "<if test=\"mediaServerId != null\">, media_server_id=#{mediaServerId}</if>" +
            "WHERE device_id=#{deviceId}"+
            " </script>"})
    void updateCustom(Device device);

    @Insert("INSERT INTO wvp_device (" +
            "device_id,"+
            "custom_name,"+
            "password,"+
            "sdp_ip,"+
            "create_time,"+
            "update_time,"+
            "charset,"+
            "ssrc_check,"+
            "as_message_channel,"+
            "broadcast_push_after_ack,"+
            "geo_coord_sys,"+
            "on_line,"+
            "media_server_id"+
            ") VALUES (" +
            "#{deviceId}," +
            "#{name}," +
            "#{password}," +
            "#{sdpIp}," +
            "#{createTime}," +
            "#{updateTime}," +
            "#{charset}," +
            "#{ssrcCheck}," +
            "#{asMessageChannel}," +
            "#{broadcastPushAfterAck}," +
            "#{geoCoordSys}," +
            "#{onLine}," +
            "#{mediaServerId}" +
            ")")
    void addCustomDevice(Device device);

    @Select("select * FROM wvp_device")
    List<Device> getAll();

    @Select("select * FROM wvp_device where  as_message_channel = true")
    List<Device> queryDeviceWithAsMessageChannel();

    @Select(" <script>" +
            "SELECT " +
            "id, " +
            "device_id, " +
            "coalesce(custom_name, name) as name, " +
            "password, " +
            "manufacturer, " +
            "model, " +
            "firmware, " +
            "transport," +
            "stream_mode," +
            "ip,"+
            "sdp_ip,"+
            "local_ip,"+
            "port,"+
            "host_address,"+
            "expires,"+
            "register_time,"+
            "keepalive_time,"+
            "create_time,"+
            "update_time,"+
            "charset,"+
            "subscribe_cycle_for_catalog,"+
            "subscribe_cycle_for_mobile_position,"+
            "mobile_position_submission_interval,"+
            "subscribe_cycle_for_alarm,"+
            "ssrc_check,"+
            "as_message_channel,"+
            "broadcast_push_after_ack,"+
            "geo_coord_sys,"+
            "on_line,"+
            "media_server_id,"+
            "(SELECT count(0) FROM wvp_device_channel dc WHERE dc.device_db_id= de.id) as channel_count " +
            " FROM wvp_device de" +
            " where 1 = 1 "+
            " <if test='status != null'> AND de.on_line=${status}</if>"+
            " <if test='query != null'> AND coalesce(custom_name, name) LIKE '%${query}%'</if> " +
            " order by create_time desc "+
            " </script>")
    List<Device> getDeviceList(@Param("query") String query, @Param("status") Boolean status);

    @Select("select * from wvp_device_channel where id = #{id}")
    DeviceChannel getRawChannel(@Param("id") int id);

    @Select("select * from wvp_device where id = #{id}")
    Device query(@Param("id") Integer id);

    @Select("select wd.* from wvp_device wd left join wvp_device_channel wdc on wd.id = wdc.device_db_id  where wdc.id = #{channelId}")
    Device queryByChannelId(@Param("channelId") Integer channelId);

    @Select("select wd.* from wvp_device wd left join wvp_device_channel wdc on wd.id = wdc.device_db_id  where wdc.device_id = #{channelDeviceId}")
    Device getDeviceBySourceChannelDeviceId(@Param("channelDeviceId") String channelDeviceId);

}
