package sistemaacademico.view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import sistemaacademico.dao.AlunoDAO;
import sistemaacademico.dao.CursoDAO;
import sistemaacademico.dao.NotaFaltaDAO;
import sistemaacademico.model.Aluno;
import sistemaacademico.model.Curso;
import sistemaacademico.model.NotaFalta;

public class PainelNotasFaltas extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtRgm;
	private JTextField txtNomeAluno;
	private JTextField txtCursoAluno;
	private JTextField txtFaltas;

	private JComboBox<String> comboDisciplina;
	private JComboBox<String> comboSemestre;
	private JComboBox<String> comboA1;
	private JComboBox<String> comboA2;
	private JComboBox<String> comboAf;

	private JTable tabelaNotas;
	private DefaultTableModel modeloTabela;

	public PainelNotasFaltas() {
		setLayout(null);
		setBorder(new LineBorder(new Color(100, 149, 237)));

		criarComponentes();
		criarTabela();
	}

	private void criarComponentes() {

		JLabel lblRgm = new JLabel("RGM");
		lblRgm.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRgm.setBounds(10, 15, 50, 25);
		add(lblRgm);

		txtRgm = new JTextField();
		txtRgm.setFont(new Font("Arial", Font.PLAIN, 16));
		txtRgm.setBounds(65, 12, 130, 28);
		add(txtRgm);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(210, 15, 50, 25);
		add(lblNome);

		txtNomeAluno = new JTextField();
		txtNomeAluno.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNomeAluno.setBounds(265, 12, 305, 28);
		txtNomeAluno.setEditable(false);
		add(txtNomeAluno);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCurso.setBounds(10, 50, 50, 25);
		add(lblCurso);

		txtCursoAluno = new JTextField();
		txtCursoAluno.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCursoAluno.setBounds(65, 47, 505, 28);
		txtCursoAluno.setEditable(false);
		add(txtCursoAluno);

		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDisciplina.setBounds(10, 87, 80, 25);
		add(lblDisciplina);

		comboDisciplina = new JComboBox<String>();
		comboDisciplina.setFont(new Font("Arial", Font.PLAIN, 15));
		comboDisciplina.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Programação Orientada a Objetos",
				"Banco de Dados",
				"Engenharia de Software",
				"Estrutura de Dados",
				"Interface Humano-Computador",
				"Redes de Computadores"
		}));
		comboDisciplina.setBounds(95, 84, 475, 28);
		add(comboDisciplina);

		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setFont(new Font("Arial", Font.PLAIN, 15));
		lblSemestre.setBounds(10, 123, 80, 25);
		add(lblSemestre);

		comboSemestre = new JComboBox<String>();
		comboSemestre.setFont(new Font("Arial", Font.PLAIN, 14));
		comboSemestre.setModel(new DefaultComboBoxModel<String>(
				new String[] { "2026-1", "2026-2", "2027-1", "2027-2" }));
		comboSemestre.setBounds(95, 120, 90, 28);
		add(comboSemestre);

		JLabel lblA1 = new JLabel("A1");
		lblA1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblA1.setBounds(195, 123, 25, 25);
		add(lblA1);

		comboA1 = new JComboBox<String>();
		comboA1.setModel(new DefaultComboBoxModel<String>(listaNotasA1A2()));
		comboA1.setBounds(220, 120, 65, 28);
		add(comboA1);

		JLabel lblA2 = new JLabel("A2");
		lblA2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblA2.setBounds(295, 123, 25, 25);
		add(lblA2);

		comboA2 = new JComboBox<String>();
		comboA2.setModel(new DefaultComboBoxModel<String>(listaNotasA1A2()));
		comboA2.setBounds(320, 120, 65, 28);
		add(comboA2);

		JLabel lblAf = new JLabel("AF");
		lblAf.setFont(new Font("Arial", Font.PLAIN, 15));
		lblAf.setBounds(395, 123, 25, 25);
		add(lblAf);

		comboAf = new JComboBox<String>();
		comboAf.setModel(new DefaultComboBoxModel<String>(listaNotasAf()));
		comboAf.setBounds(420, 120, 65, 28);
		add(comboAf);

		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setFont(new Font("Arial", Font.PLAIN, 15));
		lblFaltas.setBounds(495, 123, 50, 25);
		add(lblFaltas);

		txtFaltas = new JTextField();
		txtFaltas.setBounds(545, 120, 35, 28);
		add(txtFaltas);

		JButton btnBuscarAluno = new JButton("Buscar");
		btnBuscarAluno.setBounds(10, 165, 90, 30);
		btnBuscarAluno.addActionListener(e -> buscarAluno());
		add(btnBuscarAluno);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(120, 165, 90, 30);
		btnSalvar.addActionListener(e -> salvarNotaFalta());
		add(btnSalvar);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(230, 165, 100, 30);
		btnConsultar.addActionListener(e -> consultarNotas());
		add(btnConsultar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(350, 165, 90, 30);
		btnAlterar.addActionListener(e -> alterarNotaFalta());
		add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(460, 165, 90, 30);
		btnExcluir.addActionListener(e -> excluirNotaFalta());
		add(btnExcluir);
	}

	private void criarTabela() {

		modeloTabela = new DefaultTableModel(
				new Object[][] {},
				new String[] { "ID", "Disciplina", "Semestre", "A1", "A2", "Média", "AF", "Faltas", "Situação" });

		tabelaNotas = new JTable(modeloTabela);
		tabelaNotas.setRowHeight(22);

		JScrollPane scrollPane = new JScrollPane(tabelaNotas);
		scrollPane.setBounds(10, 210, 570, 115);
		add(scrollPane);

		tabelaNotas.getColumnModel().getColumn(0).setMinWidth(0);
		tabelaNotas.getColumnModel().getColumn(0).setMaxWidth(0);
		tabelaNotas.getColumnModel().getColumn(0).setWidth(0);

		tabelaNotas.getSelectionModel().addListSelectionListener(e -> preencherCamposPelaTabela());
	}

	private void buscarAluno() {

		try {

			String rgm = txtRgm.getText();

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

			txtNomeAluno.setText(aluno.getNome());

			CursoDAO cursoDAO = new CursoDAO();
			Curso curso = cursoDAO.consultarPorRgm(rgm);

			if (curso != null) {
				txtCursoAluno.setText(curso.getCurso());
			} else {
				txtCursoAluno.setText("Curso não cadastrado");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar aluno: " + e.getMessage());
		}
	}

	public void salvarNotaFalta() {

		try {

			String rgm = txtRgm.getText();

			if (rgm.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o RGM.");
				return;
			}

			if (txtFaltas.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe as faltas.");
				return;
			}

			String disciplina = comboDisciplina.getSelectedItem().toString();
			String semestre = comboSemestre.getSelectedItem().toString();

			NotaFaltaDAO dao = new NotaFaltaDAO();

			if (dao.existeNota(rgm, disciplina, semestre)) {
				JOptionPane.showMessageDialog(
						null,
						"Já existe nota cadastrada para esta disciplina neste semestre.\nUse Alterar para modificar."
				);
				return;
			}

			double a1 = converterNota(comboA1.getSelectedItem().toString());
			double a2 = converterNota(comboA2.getSelectedItem().toString());
			double af = converterAf(comboAf.getSelectedItem().toString());
			double media = calcularMedia(a1, a2);
			int faltas = Integer.parseInt(txtFaltas.getText());

			NotaFalta nf = new NotaFalta();

			nf.setRgm(rgm);
			nf.setDisciplina(disciplina);
			nf.setSemestre(semestre);
			nf.setA1(a1);
			nf.setA2(a2);
			nf.setAf(af);
			nf.setMedia(media);
			nf.setFaltas(faltas);
			nf.setSituacao(calcularSituacao(media, af, faltas));

			dao.salvar(nf);

			JOptionPane.showMessageDialog(null, "Nota e falta salvas com sucesso!");

			consultarNotas();
			limparCamposNota();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar nota/falta: " + e.getMessage());
		}
	}

	public void consultarNotas() {

		try {

			String rgm = txtRgm.getText();

			if (rgm.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o RGM.");
				return;
			}

			buscarAluno();

			NotaFaltaDAO dao = new NotaFaltaDAO();
			List<NotaFalta> lista = dao.consultarPorRgm(rgm);

			modeloTabela.setRowCount(0);

			for (NotaFalta nf : lista) {

				modeloTabela.addRow(new Object[] {
						nf.getIdNotaFalta(),
						nf.getDisciplina(),
						nf.getSemestre(),
						nf.getA1(),
						nf.getA2(),
						nf.getMedia(),
						formatarAf(nf.getAf()),
						nf.getFaltas(),
						nf.getSituacao()
				});
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar notas: " + e.getMessage());
		}
	}

	public void alterarNotaFalta() {

		try {

			int linha = tabelaNotas.getSelectedRow();

			if (linha < 0) {
				JOptionPane.showMessageDialog(null, "Selecione uma nota na tabela para alterar.");
				return;
			}

			if (txtFaltas.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe as faltas.");
				return;
			}

			int id = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());

			double a1 = converterNota(comboA1.getSelectedItem().toString());
			double a2 = converterNota(comboA2.getSelectedItem().toString());
			double af = converterAf(comboAf.getSelectedItem().toString());
			double media = calcularMedia(a1, a2);
			int faltas = Integer.parseInt(txtFaltas.getText());

			NotaFalta nf = new NotaFalta();

			nf.setIdNotaFalta(id);
			nf.setRgm(txtRgm.getText());
			nf.setDisciplina(comboDisciplina.getSelectedItem().toString());
			nf.setSemestre(comboSemestre.getSelectedItem().toString());
			nf.setA1(a1);
			nf.setA2(a2);
			nf.setAf(af);
			nf.setMedia(media);
			nf.setFaltas(faltas);
			nf.setSituacao(calcularSituacao(media, af, faltas));

			NotaFaltaDAO dao = new NotaFaltaDAO();
			dao.alterar(nf);

			JOptionPane.showMessageDialog(null, "Nota/falta alterada com sucesso!");

			consultarNotas();
			limparCamposNota();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao alterar nota/falta: " + e.getMessage());
		}
	}

	public void excluirNotaFalta() {

		try {

			int linha = tabelaNotas.getSelectedRow();

			if (linha < 0) {
				JOptionPane.showMessageDialog(null, "Selecione uma nota na tabela para excluir.");
				return;
			}

			int id = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());

			String disciplina = modeloTabela.getValueAt(linha, 1).toString();
			String semestre = modeloTabela.getValueAt(linha, 2).toString();

			int resposta = JOptionPane.showConfirmDialog(
					null,
					"Deseja realmente excluir esta nota?\n\n"
					+ "Disciplina: " + disciplina + "\n"
					+ "Semestre: " + semestre,
					"Confirmar exclusão",
					JOptionPane.YES_NO_OPTION
			);

			if (resposta != JOptionPane.YES_OPTION) {
				return;
			}

			NotaFaltaDAO dao = new NotaFaltaDAO();
			dao.excluir(id);

			JOptionPane.showMessageDialog(null, "Nota/falta excluída com sucesso!");

			consultarNotas();
			limparCamposNota();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir nota/falta: " + e.getMessage());
		}
	}

	private void preencherCamposPelaTabela() {

		if (tabelaNotas == null || modeloTabela == null) {
			return;
		}

		int linha = tabelaNotas.getSelectedRow();

		if (linha < 0) {
			return;
		}

		comboDisciplina.setSelectedItem(modeloTabela.getValueAt(linha, 1).toString());
		comboSemestre.setSelectedItem(modeloTabela.getValueAt(linha, 2).toString());
		comboA1.setSelectedItem(formatarNota(modeloTabela.getValueAt(linha, 3).toString()));
		comboA2.setSelectedItem(formatarNota(modeloTabela.getValueAt(linha, 4).toString()));
		comboAf.setSelectedItem(modeloTabela.getValueAt(linha, 6).toString());
		txtFaltas.setText(modeloTabela.getValueAt(linha, 7).toString());
	}

	private void limparCamposNota() {
		comboDisciplina.setSelectedIndex(0);
		comboSemestre.setSelectedIndex(0);
		comboA1.setSelectedIndex(0);
		comboA2.setSelectedIndex(0);
		comboAf.setSelectedIndex(0);
		txtFaltas.setText("");
	}

	private double calcularMedia(double a1, double a2) {
		return a1 + a2;
	}

	private String calcularSituacao(double media, double af, int faltas) {

		if (faltas > 10) {
			return "Reprovado por Falta";
		}

		if (media >= 6) {
			return "Aprovado";
		}

		if (af >= 6) {
			return "Aprovado na Final";
		}

		return "Reprovado";
	}

	private double converterNota(String nota) {
		return Double.parseDouble(nota.replace(",", "."));
	}

	private double converterAf(String nota) {

		if (nota.equals("-")) {
			return 0;
		}

		return converterNota(nota);
	}

	private String formatarNota(String nota) {
		return nota.replace(".", ",");
	}

	private String formatarAf(double af) {

		if (af == 0) {
			return "-";
		}

		return String.valueOf(af).replace(".", ",");
	}

	private String[] listaNotasA1A2() {

		return new String[] {
				"0,0", "0,5",
				"1,0", "1,5",
				"2,0", "2,5",
				"3,0", "3,5",
				"4,0", "4,5",
				"5,0"
		};
	}

	private String[] listaNotasAf() {

		return new String[] {
				"-",
				"0,0", "0,5",
				"1,0", "1,5",
				"2,0", "2,5",
				"3,0", "3,5",
				"4,0", "4,5",
				"5,0"
		};
	}
}