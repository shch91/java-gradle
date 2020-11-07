package shch91.repo.mapper.employees;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shch91.repo.daoentity.Salary;

import java.util.List;

@Mapper
public interface SalaryMapper {

      int  add(Salary salary);

     List<Salary> getByEmpNo();

     List<String> allEmpNo();

     List<Salary> getAll();
}
