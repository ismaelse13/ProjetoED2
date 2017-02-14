package ProjetoED2;


public interface IFileOrganizer {
	public void addAluno (Aluno a);
	public Aluno getAluno (long matric);
	public Aluno delAluno(long matric);
}

