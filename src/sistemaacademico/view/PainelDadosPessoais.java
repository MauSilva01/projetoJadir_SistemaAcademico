package sistemaacademico.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import sistemaacademico.dao.AlunoDAO;
import sistemaacademico.model.Aluno;

public class PainelDadosPessoais extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtRgm;
	private JTextField txtNome;
	private JFormattedTextField txtDataNascimento;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtCelular;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtMunicipio;
	private JComboBox<String> comboUf;

	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private ButtonGroup grupoSexo;

	private boolean modoEdicao = false;

	public PainelDadosPessoais() {

		setLayout(null);
		setBorder(new LineBorder(new Color(100, 149, 237)));

		criarComponentes();
		gerarRgmAutomatico();
	}

	private void criarComponentes() {

		JLabel lblRgm = new JLabel("RGM");
		lblRgm.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRgm.setBounds(10, 18, 50, 25);
		add(lblRgm);

		txtRgm = new JTextField();
		txtRgm.setFont(new Font("Arial", Font.BOLD, 18));
		txtRgm.setBounds(68, 14, 150, 29);
		txtRgm.setEditable(false);
		add(txtRgm);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(230, 18, 55, 25);
		add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNome.setBounds(285, 14, 285, 29);
		add(txtNome);

		JLabel lblDataNascimento = new JLabel("Data de Nascimento");
		lblDataNascimento.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDataNascimento.setBounds(10, 60, 150, 25);
		add(lblDataNascimento);

		txtDataNascimento = new JFormattedTextField(criarMascara("##/##/####"));
		txtDataNascimento.setFont(new Font("Arial", Font.PLAIN, 18));
		txtDataNascimento.setBounds(165, 56, 112, 29);
		add(txtDataNascimento);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCpf.setBounds(310, 60, 45, 25);
		add(lblCpf);

		txtCpf = new JFormattedTextField(criarMascara("###.###.###-##"));
		txtCpf.setFont(new Font("Arial", Font.PLAIN, 18));
		txtCpf.setBounds(350, 56, 140, 29);
		add(txtCpf);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEmail.setBounds(10, 104, 60, 25);
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 18));
		txtEmail.setBounds(68, 100, 502, 29);
		add(txtEmail);

		JLabel lblEndereco = new JLabel("End.");
		lblEndereco.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEndereco.setBounds(10, 148, 60, 25);
		add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Arial", Font.PLAIN, 18));
		txtEndereco.setBounds(68, 144, 502, 29);
		add(txtEndereco);

		JLabel lblMunicipio = new JLabel("Município");
		lblMunicipio.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMunicipio.setBounds(10, 192, 80, 25);
		add(lblMunicipio);

		txtMunicipio = new JTextField();
		txtMunicipio.setFont(new Font("Arial", Font.PLAIN, 18));
		txtMunicipio.setBounds(88, 188, 112, 29);
		add(txtMunicipio);

		JLabel lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUf.setBounds(215, 192, 30, 25);
		add(lblUf);

		comboUf = new JComboBox<String>();
		comboUf.setFont(new Font("Arial", Font.PLAIN, 14));
		comboUf.setModel(new DefaultComboBoxModel<String>(
				new String[] { "SP", "RJ", "MG", "PR", "SC", "RS", "BA", "GO" }));
		comboUf.setBounds(245, 188, 60, 29);
		add(comboUf);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCelular.setBounds(325, 192, 65, 25);
		add(lblCelular);

		txtCelular = new JFormattedTextField(criarMascara("(##)#####-####"));
		txtCelular.setFont(new Font("Arial", Font.PLAIN, 18));
		txtCelular.setBounds(390, 188, 180, 29);
		add(txtCelular);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSexo.setBounds(10, 240, 60, 25);
		add(lblSexo);

		rbMasculino = new JRadioButton("Masculino");
		rbMasculino.setFont(new Font("Arial", Font.PLAIN, 14));
		rbMasculino.setBounds(70, 240, 110, 25);
		add(rbMasculino);

		rbFeminino = new JRadioButton("Feminino");
		rbFeminino.setFont(new Font("Arial", Font.PLAIN, 14));
		rbFeminino.setBounds(180, 240, 110, 25);
		add(rbFeminino);

		grupoSexo = new ButtonGroup();
		grupoSexo.add(rbMasculino);
		grupoSexo.add(rbFeminino);
	}

	private void gerarRgmAutomatico() {

		try {
			AlunoDAO dao = new AlunoDAO();
			txtRgm.setText(dao.gerarNovoRgm());
			txtRgm.setEditable(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar RGM: " + e.getMessage());
		}
	}

	private void habilitarCampos(boolean status) {

		txtNome.setEditable(status);
		txtDataNascimento.setEditable(status);
		txtCpf.setEditable(status);
		txtEmail.setEditable(status);
		txtEndereco.setEditable(status);
		txtMunicipio.setEditable(status);
		txtCelular.setEditable(status);

		comboUf.setEnabled(status);

		rbMasculino.setEnabled(status);
		rbFeminino.setEnabled(status);
	}

	public void salvarAluno() {

		try {

			String nome = txtNome.getText();

			if (!nome.matches("[a-zA-ZÀ-ÿ ]+")) {
				JOptionPane.showMessageDialog(null, "Nome inválido! Não use números.");
				return;
			}

			String email = txtEmail.getText();

			if (!email.contains("@")) {
				JOptionPane.showMessageDialog(null, "Email inválido!");
				return;
			}

			Aluno aluno = new Aluno();

			aluno.setRgm(txtRgm.getText());
			aluno.setNome(nome);
			aluno.setDataNascimento(txtDataNascimento.getText());
			aluno.setCpf(txtCpf.getText());
			aluno.setEmail(email);
			aluno.setEndereco(txtEndereco.getText());
			aluno.setMunicipio(txtMunicipio.getText());
			aluno.setUf(comboUf.getSelectedItem().toString());
			aluno.setCelular(txtCelular.getText());

			AlunoDAO dao = new AlunoDAO();
			dao.salvar(aluno);

			JOptionPane.showMessageDialog(null, "Aluno salvo com sucesso!");

			limparCampos();
			gerarRgmAutomatico();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar aluno: " + e.getMessage());
		}
	}

	public void consultarAluno() {

		try {

			String rgm = JOptionPane.showInputDialog("Digite o RGM:");

			if (rgm == null || rgm.isEmpty()) {
				return;
			}

			AlunoDAO dao = new AlunoDAO();
			Aluno aluno = dao.consultarPorRgm(rgm);

			if (aluno == null) {
				JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
				return;
			}

			txtRgm.setText(aluno.getRgm());
			txtNome.setText(aluno.getNome());
			txtDataNascimento.setText(aluno.getDataNascimento());
			txtCpf.setText(aluno.getCpf());
			txtEmail.setText(aluno.getEmail());
			txtEndereco.setText(aluno.getEndereco());
			txtMunicipio.setText(aluno.getMunicipio());
			txtCelular.setText(aluno.getCelular());
			comboUf.setSelectedItem(aluno.getUf());

			modoEdicao = false;
			habilitarCampos(false);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar aluno: " + e.getMessage());
		}
	}

	public void alterarAluno() {

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

			String nome = txtNome.getText();

			if (!nome.matches("[a-zA-ZÀ-ÿ ]+")) {

				JOptionPane.showMessageDialog(
						null,
						"Nome inválido! Não use números."
				);

				return;
			}

			String email = txtEmail.getText();

			if (!email.contains("@")) {

				JOptionPane.showMessageDialog(
						null,
						"Email inválido!"
				);

				return;
			}

			Aluno aluno = new Aluno();

			aluno.setRgm(txtRgm.getText());
			aluno.setNome(nome);
			aluno.setDataNascimento(txtDataNascimento.getText());
			aluno.setCpf(txtCpf.getText());
			aluno.setEmail(email);
			aluno.setEndereco(txtEndereco.getText());
			aluno.setMunicipio(txtMunicipio.getText());
			aluno.setUf(comboUf.getSelectedItem().toString());
			aluno.setCelular(txtCelular.getText());

			AlunoDAO dao = new AlunoDAO();

			dao.alterar(aluno);

			JOptionPane.showMessageDialog(
					null,
					"Aluno alterado com sucesso!"
			);

			modoEdicao = false;

			habilitarCampos(false);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(
					null,
					"Erro ao alterar aluno: " + e.getMessage()
			);
		}
	}

	public void excluirAluno() {

		try {

			String rgm = txtRgm.getText();

			int resposta = JOptionPane.showConfirmDialog(
					null,
					"Deseja realmente excluir este aluno?",
					"Confirmação",
					JOptionPane.YES_NO_OPTION
			);

			if (resposta != JOptionPane.YES_OPTION) {
				return;
			}

			AlunoDAO dao = new AlunoDAO();
			dao.excluir(rgm);

			JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");

			limparCampos();
			gerarRgmAutomatico();
			habilitarCampos(true);
			modoEdicao = false;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir aluno: " + e.getMessage());
		}
	}

	public void limparCampos() {

		txtNome.setText("");
		txtDataNascimento.setText("");
		txtCpf.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");
		txtMunicipio.setText("");
		txtCelular.setText("");

		comboUf.setSelectedIndex(0);
		grupoSexo.clearSelection();
	}

	private MaskFormatter criarMascara(String mascara) {
		try {
			MaskFormatter mask = new MaskFormatter(mascara);
			mask.setPlaceholderCharacter(' ');
			return mask;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao criar máscara");
		}
	}
}