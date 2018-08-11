package web.db.dto.intellecto.android.wrappers;

import utils.general.JsonObjectMapper;
import web.db.models.intellecto.Intellecto_Robot_Info;

public class RobotInfoWrapper {

    private Intellecto_Robot_Info robotInfo;

    public RobotInfoWrapper() {}

    public static RobotInfoWrapper toRobotInfoWrapper(String robotInfoString) {
        return (RobotInfoWrapper) JsonObjectMapper.toObject(robotInfoString, RobotInfoWrapper.class);
    }

    public Intellecto_Robot_Info getRobotInfo() {
        return robotInfo;
    }

    public void setRobotInfo(Intellecto_Robot_Info robotInfo) {
        this.robotInfo = robotInfo;
    }

    @Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }

}