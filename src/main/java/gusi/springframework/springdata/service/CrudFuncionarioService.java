package gusi.springframework.springdata.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gusi.springframework.springdata.orm.Funcionario;
import gusi.springframework.springdata.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	private final FuncionarioRepository funcionarioRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {
			System.out.println("Qual ação deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar(scanner);
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}

		}
	}
	
	private void salvar(Scanner scanner) { 
		System.out.println("Nome do funcionario");
		String nome = scanner.next();
		System.out.println("CPF");
		String cpf = scanner.next();
		System.out.println("Salário");
		BigDecimal salario = scanner.nextBigDecimal();		
		
		LocalDate dataContratacao = LocalDate.now();		
		
		Funcionario funcionario= new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(dataContratacao);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Salvo com sucesso");
		
	}
	
	private void atualizar(Scanner scanner) { 
		System.out.println("Informe o id");
		int id = scanner.nextInt();
		System.out.println("Informe o novo nome");
		String novoNome = scanner.next();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(novoNome);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Atualizado");
	}
	
	private void visualizar(Scanner scanner) { 
		System.out.println("Qual página você deseja visualizar?");
		int page = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "nome"));
		
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Página atual " + funcionarios.getNumber());
		System.out.println("Total de elementos: " + funcionarios.getTotalElements());
		funcionarios.forEach(f -> System.out.println(f));
	}
	
	private void deletar(Scanner scanner) { 
		System.out.println("Digite o id");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
