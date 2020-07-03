package jp.co.sample.emp_management.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.form.RegisterEmployeeForm;
import jp.co.sample.emp_management.form.UpdateEmployeeForm;
import jp.co.sample.emp_management.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki 
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}
	@ModelAttribute
	public RegisterEmployeeForm setUpRegiserForm() {
		return new RegisterEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model, String empName) {
		List<Employee> employeeList = null;

		employeeList = employeeService.showList(empName);

		if (employeeList.size() == 0) {
			empName = null;
			employeeList = employeeService.showList(empName);
			model.addAttribute("noList","１件もありませんでした");

		}

		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id    リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
	
	
	/**
	 * オートコンプリート用に名前一覧を返すメソッド.
	 * 
	 * @return　名前一覧が格納されたマップ
	 */
	@ResponseBody
	@RequestMapping("/auteComplete")
	public Map<String , String[]> auteComplete(){
		Map<String, String[]> map=new HashMap<>();
		List<Employee> employees=employeeService.showList(null);
		String[] names= new String[employees.size()];
		for(int i=0;i<employees.size();i++) {
			names[i]=employees.get(i).getName();
		}
		map.put("empName", names);
		
		return map;
 		
	}
	
	@RequestMapping("/registerEmployee")
	public String registerEmployee()
	{
		return "employee/registerEmployee";
	}
	
	@RequestMapping("/register")
	public synchronized String register(@Validated RegisterEmployeeForm form,BindingResult result)  throws IOException {
		
		Employee existEmployee = employeeService.findByMailAddress(form.getMailAddress());
		if (existEmployee != null) {
			FieldError emailError = new FieldError(result.getObjectName(), "mailAddress", "このメールアドレスは既に登録済みです");
			result.addError(emailError);
		}

		// 画像ファイル形式チェック
		MultipartFile imageFile = form.getImage();
		String fileExtension = null;
		try {
			fileExtension = getExtension(imageFile.getOriginalFilename());

			if (!"jpg".equals(fileExtension) && !"png".equals(fileExtension)) {
				FieldError imageError = new FieldError(result.getObjectName(), "image", "拡張子は.jpgか.pngのみに対応しています");
				result.addError(imageError);
			}
		} catch (Exception e) {
			FieldError imageError = new FieldError(result.getObjectName(), "image", "拡張子は.jpgか.pngのみに対応しています");
			result.addError(imageError);
		}

		// 一つでもエラーがあれば入力画面へ戻りエラーメッセージを出す
	
		if(result.hasErrors()) {
			return registerEmployee();
		}
		
		
		Employee employee=new Employee();
		
		BeanUtils.copyProperties(form,employee);
		Date date = java.sql.Date.valueOf(form.getHireDate());
		employee.setHireDate(date);

		String base64FileString = Base64.getEncoder().encodeToString(imageFile.getBytes());
		if ("jpg".equals(fileExtension)) {
			base64FileString = "data:image/jpeg;base64," + base64FileString;
		} else if ("png".equals(fileExtension)) {
			base64FileString = "data:image/png;base64," + base64FileString;
		}
		employee.setImage(base64FileString);
		employeeService.insert(employee);
		return "redirect:/employee/showList";
	}
	
	private String getExtension(String originalFileName) throws Exception {
		if (originalFileName == null) {
			throw new FileNotFoundException();
		}
		int point = originalFileName.lastIndexOf(".");
		if (point == -1) {
			throw new FileNotFoundException();
		}
		return originalFileName.substring(point + 1);
	}
	
	
	
}
