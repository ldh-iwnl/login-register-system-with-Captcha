package hk.hku.dao;

import hk.hku.Utils.MayiktJdbcUtils;
import hk.hku.entity.HkuUserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HkuUserDao {
    /**
     * if user login success, return HkuUserEntity
     */
    public HkuUserEntity login(String userName, String userPwd){
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try{
            connection = MayiktJdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement("select * from hku_user where userName = ? and userPwd = ?");
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,userPwd);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            Integer id = resultSet.getInt(1);
            String userName1 = resultSet.getString(2);
            String userPwd1 = resultSet.getString(3);
            HkuUserEntity hkuUserEntity = new HkuUserEntity(id,userName1,userPwd1);
            return hkuUserEntity;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            MayiktJdbcUtils.closeConnection(resultSet,preparedStatement,connection);
        }
    }

    public int register(String userName, String userPwd){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = MayiktJdbcUtils.getConnection();
            MayiktJdbcUtils.beginTransaction(connection);
            preparedStatement = connection.prepareStatement("INSERT INTO `hkudb`.`hku_user` (`id`, `userName`, `userPwd`) VALUES (null, ?, ?);");
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,userPwd);
            int i = preparedStatement.executeUpdate();
            MayiktJdbcUtils.commitTransaction(connection);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
            MayiktJdbcUtils.rollBackTransaction(connection);
        }finally {
            MayiktJdbcUtils.closeConnection(preparedStatement,connection);
        }
        return 0;

    }

    public HkuUserEntity findByUsername(String userName){
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try{
            connection = MayiktJdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement("select * from hku_user where userName = ?");
            preparedStatement.setString(1,userName);

            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            Integer id = resultSet.getInt(1);
            String userName1 = resultSet.getString(2);
            String userPwd1 = resultSet.getString(3);
            HkuUserEntity hkuUserEntity = new HkuUserEntity(id,userName1,userPwd1);
            return hkuUserEntity;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            MayiktJdbcUtils.closeConnection(resultSet,preparedStatement,connection);
        }
    }
}
