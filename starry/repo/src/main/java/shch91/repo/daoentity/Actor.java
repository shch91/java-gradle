package shch91.repo.daoentity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Actor {

    public  int  actorId;

    public String firstName;

    public String lastName;

    public Date lastUpdate;

}
