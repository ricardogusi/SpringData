package gusi.springframework.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import gusi.springframework.springdata.orm.Cargo;
import gusi.springframework.springdata.orm.Unidade;
import gusi.springframework.springdata.repository.UnidadeRepository;

@Service
public class CrudUnidadeService {

	private Boolean system = true;
	private UnidadeRepository unidadeRepository; 
	
	public CrudUnidadeService(UnidadeRepository unidadeRepository) { 
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scanner) { 
		
		while(system) { 
			System.out.println("Qual ação de unidade deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch(action) { 
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
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
		System.out.println("Descrição do unidade");
		String descricao = scanner.next();
		Unidade unidade= new Unidade();
		unidade.setDescricao(descricao);
		unidadeRepository.save(unidade);
		System.out.println("Salvo com sucesso");
	}
	
	private void atualizar(Scanner scanner) { 
		System.out.println("Informe o id");
		int id = scanner.nextInt();
		System.out.println("Informe a nova Descrição");
		String novaDescricao = scanner.next();
		
		Unidade unidade= new Unidade();
		unidade.setId(id);
		unidade.setDescricao(novaDescricao);
		
		unidadeRepository.save(unidade);
		System.out.println("Atualizado");
	}
	
	private void visualizar() { 
		Iterable<Unidade> unidades= unidadeRepository.findAll();
		unidades.forEach(u-> System.out.println(u));
	}
	
	private void deletar(Scanner scanner) { 
		System.out.println("Digite o ID:");
		int id = scanner.nextInt();
		unidadeRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
	
}
