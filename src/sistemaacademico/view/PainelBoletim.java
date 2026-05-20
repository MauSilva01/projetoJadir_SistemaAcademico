package sistemaacademico.view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import sistemaacademico.dao.AlunoDAO;
import sistemaacademico.dao.CursoDAO;
import sistemaacademico.dao.NotaFaltaDAO;
import sistemaacademico.model.Aluno;
import sistemaacademico.model.Curso;
import sistemaacademico.model.NotaFalta;

public class PainelBoletim extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtRgmBoletim;
	private JTextField txtNomeBoletim;
	private JTextField txtCursoBoletim;

	private JTable tabelaBoletim;
	private DefaultTableModel modeloTabela;

	private JLabel lblMediaValor;
	private JLabel lblFaltasValor;
	private JLabel lblDisciplinasValor;

	public PainelBoletim() {

		setLayout(null);
		setBorder(new LineBorder(new Color(100, 149, 237)));

		criarComponentes();
	}

	private void criarComponentes() {

		JPanel painelIdentificacao = new JPanel();
		painelIdentificacao.setLayout(null);
		painelIdentificacao.setBorder(new TitledBorder("Identificação do Aluno"));
		painelIdentificacao.setBounds(15, 15, 565, 80);
		add(painelIdentificacao);

		JLabel lblRgm = new JLabel("RGM:");
		lblRgm.setFont(new Font("Arial", Font.BOLD, 12));
		lblRgm.setBounds(15, 25, 40, 20);
		painelIdentificacao.add(lblRgm);

		txtRgmBoletim = new JTextField();
		txtRgmBoletim.setBounds(60, 23, 180, 22);
		painelIdentificacao.add(txtRgmBoletim);

		JButton btnGerar = new JButton("Gerar");
		btnGerar.setBounds(250, 22, 80, 24);
		btnGerar.addActionListener(e -> gerarBoletim());
		painelIdentificacao.add(btnGerar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.BOLD, 12));
		lblNome.setBounds(350, 25, 50, 20);
		painelIdentificacao.add(lblNome);

		txtNomeBoletim = new JTextField();
		txtNomeBoletim.setBounds(400, 23, 150, 22);
		txtNomeBoletim.setEditable(false);
		painelIdentificacao.add(txtNomeBoletim);

		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setFont(new Font("Arial", Font.BOLD, 12));
		lblCurso.setBounds(15, 52, 50, 20);
		painelIdentificacao.add(lblCurso);

		txtCursoBoletim = new JTextField();
		txtCursoBoletim.setBounds(60, 50, 270, 22);
		txtCursoBoletim.setEditable(false);
		painelIdentificacao.add(txtCursoBoletim);

		JPanel painelTabela = new JPanel();
		painelTabela.setLayout(null);
		painelTabela.setBorder(new TitledBorder("Boletim Escolar"));
		painelTabela.setBounds(15, 100, 565, 135);
		add(painelTabela);

		modeloTabela = new DefaultTableModel(
				new Object[][] {},
				new String[] { "Disciplina", "A1", "A2", "Média", "AF", "Faltas", "Situação" });

		tabelaBoletim = new JTable(modeloTabela);
		tabelaBoletim.setFont(new Font("Arial", Font.PLAIN, 12));
		tabelaBoletim.setRowHeight(22);

		JScrollPane scrollPane = new JScrollPane(tabelaBoletim);
		scrollPane.setBounds(10, 20, 545, 105);
		painelTabela.add(scrollPane);

		tabelaBoletim.getColumnModel().getColumn(0).setPreferredWidth(145);
		tabelaBoletim.getColumnModel().getColumn(1).setPreferredWidth(40);
		tabelaBoletim.getColumnModel().getColumn(2).setPreferredWidth(40);
		tabelaBoletim.getColumnModel().getColumn(3).setPreferredWidth(50);
		tabelaBoletim.getColumnModel().getColumn(4).setPreferredWidth(40);
		tabelaBoletim.getColumnModel().getColumn(5).setPreferredWidth(50);
		tabelaBoletim.getColumnModel().getColumn(6).setPreferredWidth(110);

		JPanel cardMedia = new JPanel();
		cardMedia.setLayout(null);
		cardMedia.setBorder(new LineBorder(new Color(100, 149, 237)));
		cardMedia.setBounds(15, 250, 170, 68);
		add(cardMedia);

		JLabel lblMediaTitulo = new JLabel("Média Geral");
		lblMediaTitulo.setFont(new Font("Arial", Font.BOLD, 12));
		lblMediaTitulo.setBounds(45, 10, 100, 20);
		cardMedia.add(lblMediaTitulo);

		lblMediaValor = new JLabel("0.00");
		lblMediaValor.setFont(new Font("Arial", Font.BOLD, 24));
		lblMediaValor.setBounds(58, 32, 90, 25);
		cardMedia.add(lblMediaValor);

		JPanel cardFaltas = new JPanel();
		cardFaltas.setLayout(null);
		cardFaltas.setBorder(new LineBorder(new Color(100, 149, 237)));
		cardFaltas.setBounds(210, 250, 170, 68);
		add(cardFaltas);

		JLabel lblFaltasTitulo = new JLabel("Total Faltas");
		lblFaltasTitulo.setFont(new Font("Arial", Font.BOLD, 12));
		lblFaltasTitulo.setBounds(45, 10, 100, 20);
		cardFaltas.add(lblFaltasTitulo);

		lblFaltasValor = new JLabel("0");
		lblFaltasValor.setFont(new Font("Arial", Font.BOLD, 24));
		lblFaltasValor.setBounds(75, 32, 80, 25);
		cardFaltas.add(lblFaltasValor);

		JPanel cardDisciplinas = new JPanel();
		cardDisciplinas.setLayout(null);
		cardDisciplinas.setBorder(new LineBorder(new Color(100, 149, 237)));
		cardDisciplinas.setBounds(405, 250, 175, 68);
		add(cardDisciplinas);

		JLabel lblDisciplinasTitulo = new JLabel("Disciplinas Aprovadas");
		lblDisciplinasTitulo.setFont(new Font("Arial", Font.BOLD, 12));
		lblDisciplinasTitulo.setBounds(22, 10, 140, 20);
		cardDisciplinas.add(lblDisciplinasTitulo);

		lblDisciplinasValor = new JLabel("0/0");
		lblDisciplinasValor.setForeground(new Color(80, 80, 80));
		lblDisciplinasValor.setFont(new Font("Arial", Font.BOLD, 24));
		lblDisciplinasValor.setBounds(65, 32, 80, 25);
		cardDisciplinas.add(lblDisciplinasValor);
	}

	public void gerarBoletim() {

		try {

			String rgm = txtRgmBoletim.getText();

			if (rgm.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o RGM.");
				return;
			}

			AlunoDAO alunoDAO = new AlunoDAO();
			Aluno aluno = alunoDAO.consultarPorRgm(rgm);

			if (aluno == null) {
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
				return;
			}

			txtNomeBoletim.setText(aluno.getNome());

			CursoDAO cursoDAO = new CursoDAO();
			Curso curso = cursoDAO.consultarPorRgm(rgm);

			if (curso != null) {
				txtCursoBoletim.setText(curso.getCurso());
			} else {
				txtCursoBoletim.setText("Curso não cadastrado");
			}

			NotaFaltaDAO notaDAO = new NotaFaltaDAO();
			List<NotaFalta> lista = notaDAO.consultarPorRgm(rgm);

			modeloTabela.setRowCount(0);

			if (lista.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nenhuma nota cadastrada para este aluno.");
				limparResumo();
				return;
			}

			double somaMedias = 0;
			int totalFaltas = 0;
			int totalDisciplinas = lista.size();
			int aprovadas = 0;

			for (NotaFalta nf : lista) {

				modeloTabela.addRow(new Object[] {
						abreviarDisciplina(nf.getDisciplina()),
						nf.getA1(),
						nf.getA2(),
						nf.getMedia(),
						formatarAf(nf.getAf()),
						nf.getFaltas(),
						nf.getSituacao()
				});

				somaMedias += nf.getMedia();
				totalFaltas += nf.getFaltas();

				if (nf.getSituacao().equalsIgnoreCase("Aprovado")
						|| nf.getSituacao().equalsIgnoreCase("Aprovado na Final")) {
					aprovadas++;
				}
			}

			double mediaGeral = somaMedias / totalDisciplinas;

			lblMediaValor.setText(String.format("%.2f", mediaGeral));
			lblFaltasValor.setText(String.valueOf(totalFaltas));
			lblDisciplinasValor.setText(aprovadas + "/" + totalDisciplinas);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar boletim: " + e.getMessage());
		}
	}

	private void limparResumo() {

		modeloTabela.setRowCount(0);
		lblMediaValor.setText("0.00");
		lblFaltasValor.setText("0");
		lblDisciplinasValor.setText("0/0");
	}

	private String abreviarDisciplina(String disciplina) {

		if (disciplina.equalsIgnoreCase("Programação Orientada a Objetos")) {
			return "POO";
		}

		if (disciplina.equalsIgnoreCase("Banco de Dados")) {
			return "Banco Dados";
		}

		if (disciplina.equalsIgnoreCase("Engenharia de Software")) {
			return "Eng. Software";
		}

		if (disciplina.equalsIgnoreCase("Interface Humano-Computador")) {
			return "IHC";
		}

		if (disciplina.equalsIgnoreCase("Redes de Computadores")) {
			return "Redes";
		}

		return disciplina;
	}

	private String formatarAf(double af) {

		if (af == 0) {
			return "-";
		}

		return String.valueOf(af);
	}
}