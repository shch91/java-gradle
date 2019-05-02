package shch91.repo.mapper.employees;


import org.apache.ibatis.annotations.Mapper;
import shch91.repo.daoentity.Salary;

import java.util.List;

@Mapper
public interface SalaryMapper {

      int  add(Salary salary);

     Salary getByEmpNo(String empNo);

     List<String> allEmpNo();

     List<Salary> getAll();
}
