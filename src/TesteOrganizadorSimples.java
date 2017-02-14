package ProjetoED2;

import java.io.FileNotFoundException;

public class TesteOrganizadorSimples {
	public static void main(String[] args) {
		try {
			IFileOrganizer org = new OrganizadorSimples("alunos.db");
					
			Aluno a = new Aluno(20, "Maelsu", "Rua 22", "aloha@mail.com", (short)20);
			Aluno b = new Aluno(21, "Pablo", "Rua 22", "hello@mail.com", (short)21);
			Aluno c = new Aluno(22, "Edu", "Rua 22", "hi@mail.com", (short)22);
			Aluno d = new Aluno(23, "Toin", "Rua 22", "oitavaquestao@mail.com", (short)23);
			Aluno e = new Aluno(24, "Luan", "Rua 22", "hey@mail.com", (short)24);
			
			org.addAluno(a);
			org.addAluno(b);
			org.addAluno(c);
			org.addAluno(d);
			org.addAluno(e);
			
			//Aluno alunoDeletado2 = org.delAluno(21);
			//Aluno alunoDeletado3 = org.delAluno(22);
			//Aluno alunoDeletado4 = org.delAluno(23);
			//Aluno alunoDeletado5 = org.delAluno(24);
			
			Aluno procurado = org.getAluno(20);
			
			Aluno deletado = procurado.delAluno(20);
			System.out.println(deletado.getEmail());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
