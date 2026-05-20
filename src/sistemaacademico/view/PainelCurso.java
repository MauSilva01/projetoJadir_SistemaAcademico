package sistemaacademico.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import sistemaacademico.dao.AlunoDAO;
import sistemaacademico.dao.CursoDAO;
import sistemaacademico.model.Aluno;
import sistemaacademico.model.Curso;

public class PainelCurso extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtRgm;
	private JTextField txtNomeAluno;

	private JComboBox<String> comboCurso;
	private JComboBox<String> comboCampus;

	private JRadioButton rdbMatutino;
	private JRadioButton rdbVespertino;
	private JRadioButton rdbNoturno;

	private ButtonGroup grupoPeriodo;

	private boolean modoEdicao = false;

	public PainelCurso() {

		setLayout(null);
		setBorder(new LineBorder(new Color(100, 149, 237)));

		criarComponentes();
	}

	private void criarComponentes() {

		JLabel lblRgm = new JLabel("RGM");
		lblRgm.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRgm.setBounds(10, 18, 50, 25);
		add(lblRgm);

		txtRgm = new JTextField();
		txtRgm.setFont(new Font("Arial", Font.PLAIN, 16));
		txtRgm.setBounds(65, 14, 150, 29);
		add(txtRgm);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(230, 18, 50, 25);
		add(lblNome);

		txtNomeAluno = new JTextField();
		txtNomeAluno.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNomeAluno.setBounds(285, 14, 285, 29);
		txtNomeAluno.setEditable(false);
		add(txtNomeAluno);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCurso.setBounds(10, 62, 80, 25);
		add(lblCurso);

		comboCurso = new JComboBox<String>();
		comboCurso.setFont(new Font("Arial", Font.PLAIN, 16));
		comboCurso.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Analise e Desenvolvimento de Sistemas",
				"Ciência da Computação",
				"Sistemas de Informação",
				"Engenharia de Software",
				"Administração"
		}));
		comboCurso.setBounds(95, 58, 475, 29);
		add(comboCurso);

		JLabel lblCampus = new JLabel("Campus");
		lblCampus.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCampus.setBounds(10, 105, 80, 25);
		add(lblCampus);

		comboCampus = new JComboBox<String>();
		comboCampus.setFont(new Font("Arial", Font.PLAIN, 16));
		comboCampus.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Tatuapé",
				"Paulista",
				"Liberdade",
				"Vila Maria",
				"Santo Amaro"
		}));
		comboCampus.setBounds(95, 101, 475, 29);
		add(comboCampus);

		JLabel lblPeriodo = new JLabel("Período");
		lblPeriodo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPeriodo.setBounds(10, 148, 80, 25);
		add(lblPeriodo);

		rdbMatutino = new JRadioButton("Matutino");
		rdbMatutino.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbMatutino.setBounds(95, 148, 115, 25);
		add(rdbMatutino);

		rdbVespertino = new JRadioButton("Vespertino");
		rdbVespertino.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbVespertino.setBounds(220, 148, 130, 25);
		add(rdbVespertino);

		rdbNoturno = new JRadioButton("Noturno");
		rdbNoturno.setFont(new Font("Arial", Font.PLAIN, 16));
		rdbNoturno.setBounds(355, 148, 110, 25);
		rdbNoturno.setSelected(true);
		add(rdbNoturno);

		grupoPeriodo = new ButtonGroup();
		grupoPeriodo.add(rdbMatutino);
		grupoPeriodo.add(rdbVespertino);
		grupoPeriodo.add(rdbNoturno);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(65, 220, 100, 30);
		btnSalvar.addActionListener(e -> salvarCurso());
		add(btnSalvar);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(180, 220, 110, 30);
		btnConsultar.addActionListener(e -> consultarCurso());
		add(btnConsultar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(305, 220, 100, 30);
		btnAlterar.addActionListener(e -> alterarCurso());
		add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(420, 220, 100, 30);
		btnExcluir.addActionListener(e -> excluirCurso());
		add(btnExcluir);
	}

	public void salvarCurso() {

		try {

			String rgm = txtRgm.getText();

			if (rgm.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe o RGM do aluno.");
				return;
			}

			AlunoDAO alunoDAO = new AlunoDAO();
			Aluno aluno = alunoDAO.consultarPorRgm(rgm);

			if (aluno == null) {
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
				return;
			}

			CursoDAO cursoDAO = new CursoDAO();

			if (cursoDAO.consultarPorRgm(rgm) != null) {
				JOptionPane.showMessageDialog(null, "Este aluno já possui curso cadastrado.");
				return;
			}

			Curso curso = new Curso();

			curso.setRgm(rgm);
			curso.setCurso(comboCurso.getSelectedItem().toString());
			curso.setCampus(comboCampus.getSelectedItem().toString());
			curso.setPeriodo(getPeriodoSelecionado());

			cursoDAO.salvar(curso);

			txtNomeAluno.setText(aluno.getNome());

			JOptionPane.showMessageDialog(null, "Curso salvo com sucesso!");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar curso: " + e.getMessage());
		}
	}

	public void consultarCurso() {

		try {

			String rgm = JOptionPane.showInputDialog("Digite o RGM do aluno:");

			if (rgm == null || rgm.isEmpty()) {
				return;
			}

			AlunoDAO alunoDAO = new AlunoDAO();
			Aluno aluno = alunoDAO.consultarPorRgm(rgm);

			if (aluno == null) {
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
				return;
			}

			CursoDAO cursoDAO = new CursoDAO();
			Curso curso = cursoDAO.consultarPorRgm(rgm);

			if (curso == null) {
				JOptionPane.showMessageDialog(null, "Curso não cadastrado para este aluno.");
				txtRgm.setText(rgm);
				txtNomeAluno.setText(aluno.getNome());
				return;
			}

			txtRgm.setText(aluno.getRgm());
			txtNomeAluno.setText(aluno.getNome());

			comboCurso.setSelectedItem(curso.getCurso());
			comboCampus.setSelectedItem(curso.getCampus());
			setPeriodoSelecionado(curso.getPeriodo());

			habilitarCampos(false);
			modoEdicao = false;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar curso: " + e.getMessage());
		}
	}

	public void alterarCurso() {

		try {

			if (!modoEdicao) {

				modoEdicao = true;
				habilitarCampos(true);

				JOptionPane.showMessageDialog(
						null,
						"ATENÇÃO!\n\n"
						+ "Os campos foram liberados para edição.\n\n"
						+ "Após realizar as alterações,\n"
						+ "clique novamente em ALTERAR para salvar."
				);

				return;
			}

			Curso curso = new Curso();

			curso.setRgm(txtRgm.getText());
			curso.setCurso(comboCurso.getSelectedItem().toString());
			curso.setCampus(comboCampus.getSelectedItem().toString());
			curso.setPeriodo(getPeriodoSelecionado());

			CursoDAO dao = new CursoDAO();
			dao.alterar(curso);

			JOptionPane.showMessageDialog(null, "Curso alterado com sucesso!");

			modoEdicao = false;
			habilitarCampos(false);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao alterar curso: " + e.getMessage());
		}
	}

	public void excluirCurso() {

		try {

			String rgm = txtRgm.getText();

			if (rgm.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Consulte um curso antes de excluir.");
				return;
			}

			int resposta = JOptionPane.showConfirmDialog(
					null,
					"Deseja realmente excluir o curso deste aluno?",
					"Confirmação",
					JOptionPane.YES_NO_OPTION
			);

			if (resposta != JOptionPane.YES_OPTION) {
				return;
			}

			CursoDAO dao = new CursoDAO();
			dao.excluir(rgm);

			JOptionPane.showMessageDialog(null, "Curso excluído com sucesso!");

			limparCampos();
			habilitarCampos(true);
			modoEdicao = false;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir curso: " + e.getMessage());
		}
	}

	private String getPeriodoSelecionado() {

		if (rdbMatutino.isSelected()) {
			return "Matutino";
		}

		if (rdbVespertino.isSelected()) {
			return "Vespertino";
		}

		return "Noturno";
	}

	private void setPeriodoSelecionado(String periodo) {

		if (periodo.equalsIgnoreCase("Matutino")) {
			rdbMatutino.setSelected(true);
		} else if (periodo.equalsIgnoreCase("Vespertino")) {
			rdbVespertino.setSelected(true);
		} else {
			rdbNoturno.setSelected(true);
		}
	}

	private void habilitarCampos(boolean status) {

		comboCurso.setEnabled(status);
		comboCampus.setEnabled(status);

		rdbMatutino.setEnabled(status);
		rdbVespertino.setEnabled(status);
		rdbNoturno.setEnabled(status);
	}

	private void limparCampos() {

		txtRgm.setText("");
		txtNomeAluno.setText("");

		comboCurso.setSelectedIndex(0);
		comboCampus.setSelectedIndex(0);

		rdbNoturno.setSelected(true);
	}
}