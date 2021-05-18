package gusi.springframework.springdata;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gusi.springframework.springdata.service.CrudCargoService;
import gusi.springframework.springdata.service.CrudFuncionarioService;
import gusi.springframework.springdata.service.CrudUnidadeService;
import gusi.springframework.springdata.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner{

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeService unidadeService; 
	private final RelatoriosService relatoriosService;
	
	private Boolean system = true;
	
	public SpringDataApplication(CrudCargoService cargoService, 
						  CrudFuncionarioService funcionarioService,
						  CrudUnidadeService unidadeService,
						  RelatoriosService relatorioService) { 
		this.cargoService	= cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeService = unidadeService;
		this.relatoriosService = relatorioService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Scanner  scanner = new Scanner(System.in);
		
		while(system) { 
			System.out.println("Qual ação você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatórios");
			
			
			int action = scanner.nextInt();
			switch(action) {
			
			case 1:  
				cargoService.inicial(scanner);
				break;
			case 2:
				funcionarioService.inicial(scanner);
				break;
			case 3:
				unidadeService.inicial(scanner);
				break;
			case 4:
				relatoriosService.inicial(scanner);
			default:
				system = false;
				break;
			
			}
		}
		
		
	}

}
