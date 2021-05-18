package gusi.springframework.springdata.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import gusi.springframework.springdata.orm.Funcionario;
import gusi.springframework.springdata.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) { 
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) { 
		
		while(system) { 
			System.out.println("Qual ação de cargo deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar funcionário por nome");
			System.out.println("2 - Buscar funcionário por nome, salário e data de contratação");
			System.out.println("3 - Buscar funcionário data de contratação");
			int action = scanner.nextInt();
			
			switch(action) { 
			case 1:
				buscarPorNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			
			default:
				system = false;
				break;
			}
		}		
	}
	
	private void buscarPorNome(Scanner scanner) { 
		System.out.println("Digite o nome");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(f -> System.out.println(f));
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) { 
		System.out.println("Qual o nome?");
		String nome = scanner.next();
		
		System.out.println("Qual a data?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.println("Qual salário?");
		BigDecimal salario = scanner.nextBigDecimal();

		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(f -> System.out.println(f));
		
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) { 
		System.out.println("Qual a data?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
}
