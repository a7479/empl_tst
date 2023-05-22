package sky.pro.empl_tst.Service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sky.pro.empl_tst.Employee;
import sky.pro.empl_tst.Exception.EmployeeAlreadyAddedException;
import sky.pro.empl_tst.Exception.EmployeeNotFoundException;
import sky.pro.empl_tst.Exception.InvalidInputException;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
public class EmployeeService {

    private final List<Employee> staff = new ArrayList<>();


    public Employee addEmployee(String firstName, String lastName, Integer departmentId, Double salary) {

        validateName(firstName, lastName);

        Employee temp = new Employee(firstName, lastName, departmentId, salary);
        if (staff.contains(temp)) {
            throw new EmployeeAlreadyAddedException();
        }


        staff.add(temp);
        return temp;


    }

    public Employee removeEmployee(String firstName, String lastName, Integer departmentId, Double salary) {

        validateName(firstName, lastName);

        Employee temp = new Employee(firstName, lastName, departmentId, salary);
        if (staff.contains(temp)) {
            staff.remove(temp);
            return temp;
        }

        throw new EmployeeAlreadyAddedException();


    }

    public Employee findEmployee(String firstName, String lastName, Integer departmentId, Double salary) {

        validateName(firstName, lastName);

        Employee temp = new Employee(firstName, lastName, departmentId, salary);
        if (staff.contains(temp)) {
            return temp;
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> findAll() {
        return Collections.unmodifiableList(staff);
    }


    private void validateName(String firstName, String lastName) {
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}

