package shch91.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

@Slf4j
@Component
public class ZkMaster {

    private static final String MASTER = "/master";

    private static final String serverId = "1";

    private boolean isLeader = false;

    @Autowired
    @Qualifier(value = "zk")
    private  ZooKeeper zk;

    private boolean checkMaster()throws InterruptedException{
        while (true) {
            try {
                Stat stat = new Stat();

                byte[] data = zk.getData(MASTER, false, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            }catch (KeeperException.NoNodeException e){
                return  false;
            }catch (KeeperException.ConnectionLossException e){
                //log.info("[ZkMaster.checkMaster] connection lossed");
            }  catch (KeeperException e) {
                //log.info("[ZkMaster.KeeperException] error");
            }
        }
    }

    private void  runForMaster() throws InterruptedException{
        while (true){
            try{
                zk.create(MASTER,serverId.getBytes(),OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader=true;
                break;
            }catch (KeeperException.NodeExistsException e){
                isLeader=false;
                break;
            }catch (KeeperException.ConnectionLossException e){
               // log.info("[ZkMaster.runForMaster] connection lossed");
            } catch (KeeperException e) {
               // log.info("[ZkMaster.KeeperException] error");
            }
            if(checkMaster()){
                break;
            }
        }
    }


}
