package com.ght.graduationsys;


import com.ght.graduationsys.dao.*;
import com.ght.graduationsys.entity.*;
import com.ght.graduationsys.service.PermissionModuleService;
import com.ght.graduationsys.service.StudentService;
import com.ght.graduationsys.service.TaskItemService;
import com.ght.graduationsys.service.TeacherService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraduationsysApplicationTests {
    @Autowired
    private TaskItemService taskItemService;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private PermissionModuleService permissionModuleService;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private TaskItemDeclareDao taskItemDeclareDao;

    @Autowired
    private TaskItemSignUpDao taskItemSignUpDao;

    @Test
    public void contextLoads() {
        System.out.println(majorMapper.getAllMajors());
    }

    @Test
    public void contextLoads1() {
        System.out.println(collegeMapper.getAllColleges());
    }

    @Test
    public void contextLoads2() {
        System.out.println(classMapper.getClassesByMid(1));
    }

    @Test
    public void contextLoads3() {
        System.out.println(studentService.getClassById("16115071033"));
    }



    @Test
    public void contextLoads4() {
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);
    }

    @Test
    public void contextLoads5() {
        System.out.println(permissionModuleService.getPermissionModules());
    }

    @Test
    public void contextLoads6() {
        Object obj=new SimpleHash("md5","16115072033",null,2);
        System.out.println(obj);
    }

    @Test
    public void contextLoads7() {

        System.out.println(roleMapper.getRoleById(1));
    }

    @Test
    public void contextLoads8() {
        Student student=studentService.getStudentById("16115071033");
        List<Role> roleList=student.getRoleList();

        LinkedHashMap myLinkedHashMap=new LinkedHashMap();
        for (Role role:roleList){
            List<Permission> permissionList=role.getPermissionList();
            for (Permission permission:permissionList){
                PermissionModule pm=permissionModuleService.getPermissionModuleById(permission.getPmid());
                myLinkedHashMap.put(pm,permission);
            }
        }


        for (Object obj : myLinkedHashMap.entrySet()) {
            Map.Entry<PermissionModule, Permission> entry=(Map.Entry<PermissionModule, Permission>)obj;
            entry.getKey().setList((List<Permission>) entry.getValue());



        }

        System.out.println(myLinkedHashMap.keySet());



    }

    @Test
    public void contextLoads9() {
        System.out.println(studentService.getStudentById("16115071033"));
    }

    @Test
    public void contextLoads10() {
        System.out.println(reportMapper.getReportsByTid("rg002"));
    }


    @Test
    public void contextLoads12() {
        Teacher teacher=teacherService.getTeacherById("rg001");
        LinkedHashMap<PermissionModule,List<Permission>> menuMap=new LinkedHashMap<>();
        List<Role> roleList=teacher.getRoleList();

        for (Role role:roleList){
            List<Permission> permissionList=role.getPermissionList();//当前角色所有的权限permissionList
            for (Permission permission:permissionList){
                int pmid=permission.getPmid();
                PermissionModule permissionModule=permissionModuleService.getPermissionModuleById(pmid);//当前遍历的权限所属的权限模块
                PermissionModule pm2=null;
                if (menuMap.isEmpty()){
                    List<Permission> permissions=new ArrayList<>();
                    permissions.add(permission);
                    menuMap.put(permissionModule,permissions);
                }else{
                    Set<PermissionModule> pms=menuMap.keySet();//当前菜单所有保存的权限模块
                    boolean flag=false;
                    for (PermissionModule pm1 : pms){
                        if (pm1.getName().equals(permissionModule.getName())){
                            flag=true;
                            pm2=pm1;
                            break;
                        }else{
                            flag=false;
                        }
                    }


                    if (flag==false){
                        List<Permission> permissions=new ArrayList<>();
                        permissions.add(permission);
                        menuMap.put(permissionModule,permissions);
                    }else{
                        List<Permission> permissions=menuMap.get(pm2);
                        boolean flag1=false;
                        for (Permission p:permissions){
                            if (p.getName().equals(permission.getName())){
                                flag1=true;
                                break;
                            }else{
                                flag1=false;
                            }
                        }
                        if (flag1==false){
                            permissions.add(permission);
                        }
                    }
                }
            }
        }

        System.out.println(menuMap);

    }


    @Test
    public void contextLoads13() {
        System.out.println(studentService.getStudentById("16115071033"));
    }

    @Test
    public void contextLoads14() {
        System.out.println(taskItemSignUpDao.getTaskItemSignUpByStudentid("16115071033"));

    }

    @Test
    public void contextLoads15() {
        taskItemSignUpDao.deleteTaskItemSignUpByDeclareid("9962e93ce67b4f3fa211dbf2996ebf11");

    }





}
