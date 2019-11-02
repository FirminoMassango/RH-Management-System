/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import controlo.CtrlFerias;
import controlo.CtrlFuncionario;
import controlo.CtrlRemuneracao;
import controlo.CtrlSalario;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Ferias;
import modelo.Funcionario;
import modelo.Numeros;
import modelo.Remuneracao;
import modelo.Salario;

/**
 *
 * @author Firmino Massango
 */
public final class TelaPrincipal extends javax.swing.JFrame {

    CardLayout cardLayout;


    /**
     * Construtor
     */
    public TelaPrincipal() {
        initComponents();

        //setIcon();
        cardLayout = (CardLayout) painelPrincipal.getLayout();

        botaoInicio();
        listarFuncionarioDemo(CtrlFuncionario.getTodosDAOdecrescente());
        listarFeriasDemo(CtrlFerias.getTodosDAOdecrescente());
        listarFuncionarioActivo();
        listarFuncionario(CtrlFuncionario.getTodosDAO());
        listarFerias(CtrlFerias.getTodosDAO());
        listarFuncionarioCB();
        listarFuncionarioFaltaCB();
        listarFuncRegCB();
        listarFuncCB();
        listarFuncionarioEfectCB();
        preencherLabel();
        cbFuncionarioReg.setEnabled(false);
        txtNUIT.setDocument(new Numeros());

    }
    
/**
 * Método para listar funcionários na JTable
 * @param listarFuncionario 
 */
    public void listarFuncionario(List<Funcionario> listarFuncionario) {

        DefaultTableModel modelo = new DefaultTableModel();

        this.tabelaFuncionario.setModel(modelo);

        String colunas[] = {"Id", "Nome", "Apelido","NUIT", "BI","Faltas","Salário","Data de Nascimento","Género", "Endereco", "Telefone",
            "Email", "Profissão", "Data de Ingresso", "Data do Fim do contracto", "Nível Académico","Instituição"};

        for (String coluna : colunas) {
            modelo.addColumn(coluna);
        }
        
       

        for (Funcionario func : listarFuncionario) {
            modelo.addRow(new Object[]{
                func.getId(),
                func.getNome(),
                func.getApelido(),
                func.getNuit(),
                func.getBi(),
                func.getNrFaltas(),
                func.getSalarioBase(),
                func.getDataNascimento(),
                func.getGenero(),
                func.getEndereco(),
                func.getNumeroTelefone(),
                func.getEmail(),
                func.getProfissao(),
                func.getDataIngresso(),
                func.getDataFimDoContracto(),
                func.getNivelAcademico(),
                func.getInstituicao()
            });
        }
    }

    
    /**
     * Método para Listar Funcionários activos na JTable
     */
       public void listarFuncionarioActivo() {

        DefaultTableModel modelo = new DefaultTableModel();
           CtrlFuncionario dao = new CtrlFuncionario();

        this.tabelaFuncionario.setModel(modelo);

        String colunas[] = {"Id", "Nome", "Apelido","NUIT", "BI","Faltas","Salário","Data de Nascimento","Género", "Endereco", "Telefone",
            "Email", "Profissão", "Data de Ingresso", "Data do Fim do contracto", "Nível Académico","Instituição"};

        for (String coluna : colunas) {
            modelo.addColumn(coluna);
        }
        
       

        for (Funcionario func : dao.listarActivos("")) {
            modelo.addRow(new Object[]{
                func.getId(),
                func.getNome(),
                func.getApelido(),
                func.getNuit(),
                func.getBi(),
                func.getNrFaltas(),
                func.getSalarioBase(),
                func.getDataNascimento(),
                func.getGenero(),
                func.getEndereco(),
                func.getNumeroTelefone(),
                func.getEmail(),
                func.getProfissao(),
                func.getDataIngresso(),
                func.getDataFimDoContracto(),
                func.getNivelAcademico(),
                func.getInstituicao()
            });
        }
    }
   
    
    
       /**
     * Método para listar dados sobre as férias na JTable de demonstração
     * @param listarFerias 
     */
    public void listarFerias(List<Ferias> listarFerias) {

        DefaultTableModel modelo = new DefaultTableModel();

        //DefaultTableModel modelo = (DefaultTableModel) tabelaDemoFunc.getModel();
        this.tabelaFerias.setModel(modelo);

        String colunas[] = {"Nome", "Apelido", "Início",
            "Fim", "Dias","Observações"};

        for (String coluna : colunas) {
            modelo.addColumn(coluna);
        }

        for (Ferias fer : listarFerias) {
            modelo.addRow(new Object[]{
                fer.getNome(),
                fer.getApelido(),
                fer.getDataInicio(),
                fer.getDataFim(),
                fer.getDiasDeFerias(),
                fer.getObservacoes()

            });
        }
    }
    
    /**
     * Método para listar dados sobre as férias na JTable de demonstração
     * @param listarFerias 
     */
    public void listarFeriasDemo(List<Ferias> listarFerias) {

        DefaultTableModel modelo = new DefaultTableModel();

        //DefaultTableModel modelo = (DefaultTableModel) tabelaDemoFunc.getModel();
        this.tabelaDemoFer.setModel(modelo);

        String colunas[] = {"Nome", "Apelido", "Início",
            "Fim", "Dias","Observações"};

        for (String coluna : colunas) {
            modelo.addColumn(coluna);
        }

        for (Ferias fer : listarFerias) {
            modelo.addRow(new Object[]{
                fer.getNome(),
                fer.getApelido(),
                fer.getDataInicio(),
                fer.getDataFim(),
                fer.getDiasDeFerias(),
                fer.getObservacoes()

            });
        }
    }

    
    
     /**
     * Método para listar funcionários na JTable de demonstração
     * @param listarFuncionario 
     */
    public void listarFuncionarioDemo(List<Funcionario> listarFuncionario) {

        DefaultTableModel modelo = new DefaultTableModel();

        this.tabelaDemoFuncionario.setModel(modelo);

        String colunas[] = {"Id", "Nome", "Apelido", "NUIT", "BI", 
            "Endereco", "Telefone", "Email", "Profissão"};

        for (String coluna : colunas) {
            modelo.addColumn(coluna);
        }

        for (Funcionario func : listarFuncionario) {
            modelo.addRow(new Object[]{
                func.getId(),
                func.getNome(),
                func.getApelido(),
                func.getNuit(),
                func.getBi(),
                func.getEndereco(),
                func.getNumeroTelefone(),
                func.getEmail(),
                func.getProfissao()});
        }
    }

 
    /**
     * Configuração do botão Início
     * 
     */
    public void botaoInicio() {

        cardLayout.show(painelPrincipal, "card9");
        btnInicio.setBackground(new Color(0, 153, 153));
        btnAgenda.setBackground(new Color(0, 153, 255));

        btnRegisto.setBackground(new Color(0, 153, 255));

        btnRelatorio.setBackground(new Color(0, 153, 255));
        
    }

    /**
     * Campos de registo
     */
    public void camposRegisto() {

        if (cbProfissao.getSelectedIndex() == 0) {
            cbProfissao.setBackground(Color.red);
            cbProfissao.requestFocus();
        }

        if (txtEmail.getText().isEmpty()) {
            txtEmail.setBackground(Color.red);
            txtEmail.requestFocus();
        }

        if (txtNumeroTelefone.getText().isEmpty()) {
            txtNumeroTelefone.setBackground(Color.red);
            txtNumeroTelefone.requestFocus();
        }

        if (txtEndereco.getText().isEmpty()) {
            txtEndereco.setBackground(Color.red);
            txtEndereco.requestFocus();
        }

        if (cbGenero.getSelectedIndex() == 0) {
            cbGenero.setBackground(Color.red);
            cbGenero.requestFocus();
        }

        if (txtBI.getText().isEmpty()) {
            txtBI.setBackground(Color.red);
            txtBI.requestFocus();
        }

        if (txtNUIT.getText().isEmpty()) {
            txtNUIT.setBackground(Color.red);
            txtNUIT.requestFocus();
        }

        if (txtApelido.getText().isEmpty()) {
            txtApelido.setBackground(Color.red);
            txtApelido.requestFocus();
        }

        if (txtNome.getText().isEmpty()) {
            txtNome.setBackground(Color.red);
            txtNome.requestFocus();
        }

    }

    public void listarFuncionarioCB() {

        CtrlFuncionario dao = new CtrlFuncionario();

        //for (Funcionario func : listarFuncionario) {
       for (Funcionario func : dao.listarActivos("")) {
            
            cbFuncionario.addItem(func);

        }
    }
    
    
    /**
     * Método para preencher o componente ComboBox Funcionário 
     * para a atribuição de faltas
     */
    public void listarFuncionarioFaltaCB() {
        CtrlFuncionario dao = new CtrlFuncionario();

        //for (Funcionario func : listarFuncionario) {
       for (Funcionario func : dao.listarActivos("")) {
           
            cbFuncionarioFalta.addItem(func);

        }
    }
    
    
      /**
     * Método para preencher o componente ComboBox Funcionário 
     * para a atribuição de faltas
     */
    public void listarFuncionarioEfectCB() {
        CtrlFuncionario dao = new CtrlFuncionario();

        //for (Funcionario func : listarFuncionario) {
       for (Funcionario func : dao.listarActivos("")) {
           
            cbEfect.addItem(func);

        }
    }
    
       public void listarFuncCB() {
        CtrlFuncionario dao = new CtrlFuncionario();

        for (Funcionario func : dao.listarActivos("")) {

            cbFunc.addItem(func);
        }
    }
            
    public void listarFuncRegCB() {
        CtrlFuncionario dao = new CtrlFuncionario();

        for (Funcionario func : dao.listarActivos("")) {

            cbFuncionarioReg.addItem(func);
        }
    }
            
            
            public void remover(){
              
        
        //Verificando se a linha foi seleccionada
           if (tabelaFuncionario.getSelectedRow() != -1) {
            Funcionario funcionario = new Funcionario();
            CtrlFuncionario dao = new CtrlFuncionario();

            //Pegando o ID
            String d = tabelaFuncionario.getValueAt(tabelaFuncionario.getSelectedRow(), 0).toString();
            funcionario.setId(Integer.parseInt(d));
            
            int linha = tabelaFuncionario.getSelectedRow();
                
                funcionario.setNome((String) tabelaFuncionario.getValueAt(linha, 1));
                funcionario.setApelido((String) tabelaFuncionario.getValueAt(linha, 2));
                funcionario.setNuit((int) tabelaFuncionario.getValueAt(linha, 3));
                funcionario.setBi((String) tabelaFuncionario.getValueAt(linha, 4));
                funcionario.setNrFaltas((int) tabelaFuncionario.getValueAt(linha, 5));
                funcionario.setSalarioBase((double) tabelaFuncionario.getValueAt(linha, 6));
                funcionario.setDataNascimento((Date) tabelaFuncionario.getValueAt(linha, 7));
                funcionario.setGenero((String) tabelaFuncionario.getValueAt(linha, 8));
                funcionario.setEndereco((String) tabelaFuncionario.getValueAt(linha, 9));
                funcionario.setNumeroTelefone((String) tabelaFuncionario.getValueAt(linha, 10));
                funcionario.setEmail((String) tabelaFuncionario.getValueAt(linha, 11));
                funcionario.setProfissao((String) tabelaFuncionario.getValueAt(linha, 12));
                funcionario.setDataIngresso((Date) tabelaFuncionario.getValueAt(linha, 13));
                funcionario.setDataFimDoContracto((Date) tabelaFuncionario.getValueAt(linha, 14));
                funcionario.setNivelAcademico((String) tabelaFuncionario.getValueAt(linha, 15));
                funcionario.setInstituicao((String) tabelaFuncionario.getValueAt(linha, 16));
                funcionario.setEstado("inactivo");
            
            
            
            
            

            //JOptionPane
            int resposta = JOptionPane.showConfirmDialog(null, "Realmente deseja Eliminar o funcionário?", "Confirmar", JOptionPane.YES_NO_OPTION);

            //Verificando se a opção YES foi seleccionada, caso a condição se verifique o
            //funcionário será removido
            if (resposta == JOptionPane.YES_OPTION) {
                dao.removerFuncionario(funcionario);

            //Caso a tecla NO seja seleccionada, nada será feito
            } else {
                new TelaPrincipal().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }

            listarFuncionarioActivo();

        } else {
            JOptionPane.showMessageDialog(null,
                    "Selecione um Funcionário para poder Remover");
        }
                
            }
            
            
            
         public void preencherLabel() {
        // TODO add your handling code here:
        int linha = tabelaFuncionario.getSelectedRow();

        if (linha != -1) {
            
            lbNomeFunc.setText((String) tabelaFuncionario.getValueAt(linha, 1));
            lbApelidoFunc.setText((String) tabelaFuncionario.getValueAt(linha, 2));
            lbProfissaoFunc.setText((String) tabelaFuncionario.getValueAt(linha, 12));

        }
    }
     
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        painelEsquerdo = new javax.swing.JPanel();
        btnAgenda = new javax.swing.JButton();
        btnRelatorio = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnRegisto = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        painelPrincipal = new javax.swing.JPanel();
        painelFaltas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cbFuncionarioFalta = new javax.swing.JComboBox<>();
        cbMotivo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        chbFalta = new javax.swing.JCheckBox();
        lbId = new javax.swing.JLabel();
        lbProfissao = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnConfirmarFalta = new javax.swing.JButton();
        btnVoltarFaltas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOBS = new javax.swing.JTextArea();
        painelFerias = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cbFuncionario = new javax.swing.JComboBox<>();
        cbDias = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextArea();
        dataInicio = new com.toedter.calendar.JDateChooser();
        dataFim = new com.toedter.calendar.JDateChooser();
        btnConfirmarFerias = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        painelFuncionarios = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbNome = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        lbNomeFunc = new javax.swing.JLabel();
        lbApelidoFunc = new javax.swing.JLabel();
        lbProfissaoFunc = new javax.swing.JLabel();
        chbFuncionariosDemitidos = new javax.swing.JCheckBox();
        btnEfectividade = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaFuncionario = new javax.swing.JTable();
        btnVoltarFuncionario = new javax.swing.JButton();
        painelPagamentoDeSalario = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbFunc = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        btnConf = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        lbSal = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        chbSubFer = new javax.swing.JCheckBox();
        chbSubDecimoTer = new javax.swing.JCheckBox();
        painelPerFerias = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaFerias = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        painelSalario = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtContabilista = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCozinheiro = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtElectrecista = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtFrentista = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtGuarda = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtMecanico = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtSubAlim = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtSubTransp = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtBonus = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtMotorista = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtSerrelheiro = new javax.swing.JTextField();
        btnConfirmar = new javax.swing.JButton();
        btnVoltar1 = new javax.swing.JButton();
        btnEditarSal = new javax.swing.JButton();
        painelRegisto = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtNUIT = new javax.swing.JTextField();
        dcNascimento = new com.toedter.calendar.JDateChooser();
        txtEndereco = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        dcIngresso = new com.toedter.calendar.JDateChooser();
        jLabel51 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtApelido = new javax.swing.JTextField();
        txtBI = new javax.swing.JTextField();
        cbGenero = new javax.swing.JComboBox<>();
        txtNumeroTelefone = new javax.swing.JTextField();
        cbProfissao = new javax.swing.JComboBox<>();
        dcFimContracto = new com.toedter.calendar.JDateChooser();
        btnConfReg = new javax.swing.JButton();
        btnVoltarReg = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        cbNvlAcademico = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        txtInstit = new javax.swing.JTextField();
        cbFuncionarioReg = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        rbCompetente = new javax.swing.JRadioButton();
        rbDedicado = new javax.swing.JRadioButton();
        rbDisciplinado = new javax.swing.JRadioButton();
        rbFlexivel = new javax.swing.JRadioButton();
        rbFormacao = new javax.swing.JRadioButton();
        cbTipoAdmissao = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        painelInicioo = new javax.swing.JPanel();
        maisFunc = new javax.swing.JButton();
        maisFer = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        painelDemoFunc = new javax.swing.JScrollPane();
        tabelaDemoFuncionario = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabelaDemoFer = new javax.swing.JTable();
        painelRegistoo = new javax.swing.JPanel();
        btnFaltas = new javax.swing.JButton();
        btnSalario = new javax.swing.JButton();
        btnRegFunc = new javax.swing.JButton();
        btnDefSalario = new javax.swing.JButton();
        painelEfectividade = new javax.swing.JPanel();
        cbEfect = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lbCompetente = new javax.swing.JLabel();
        lbDedicado = new javax.swing.JLabel();
        lbDisciplinado = new javax.swing.JLabel();
        lbFlexivel = new javax.swing.JLabel();
        lbFormacaoAd = new javax.swing.JLabel();
        lbTipoAdmissao = new javax.swing.JLabel();
        btnVoltarEfect = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Click");

        jSplitPane1.setDividerSize(0);

        painelEsquerdo.setBackground(new java.awt.Color(0, 153, 204));

        btnAgenda.setBackground(new java.awt.Color(0, 153, 255));
        btnAgenda.setFont(new java.awt.Font("Clear Sans", 1, 14)); // NOI18N
        btnAgenda.setForeground(new java.awt.Color(255, 255, 255));
        btnAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/Agendamento.png"))); // NOI18N
        btnAgenda.setText(" AGENDAMENTO");
        btnAgenda.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaActionPerformed(evt);
            }
        });

        btnRelatorio.setBackground(new java.awt.Color(0, 153, 255));
        btnRelatorio.setFont(new java.awt.Font("Clear Sans", 1, 14)); // NOI18N
        btnRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        btnRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/Relatório.png"))); // NOI18N
        btnRelatorio.setText(" RELATÓRIO");
        btnRelatorio.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });

        btnSair.setBackground(new java.awt.Color(0, 153, 255));
        btnSair.setFont(new java.awt.Font("Clear Sans", 1, 14)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/Sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/TheClik.png"))); // NOI18N

        btnRegisto.setBackground(new java.awt.Color(0, 153, 255));
        btnRegisto.setFont(new java.awt.Font("Clear Sans", 1, 14)); // NOI18N
        btnRegisto.setForeground(new java.awt.Color(255, 255, 255));
        btnRegisto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/Registo.png"))); // NOI18N
        btnRegisto.setText("REGISTO");
        btnRegisto.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnRegisto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistoActionPerformed(evt);
            }
        });

        btnInicio.setBackground(new java.awt.Color(0, 153, 255));
        btnInicio.setFont(new java.awt.Font("Clear Sans", 1, 14)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/Início.png"))); // NOI18N
        btnInicio.setText(" INÍCIO");
        btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnInicioMouseReleased(evt);
            }
        });
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelEsquerdoLayout = new javax.swing.GroupLayout(painelEsquerdo);
        painelEsquerdo.setLayout(painelEsquerdoLayout);
        painelEsquerdoLayout.setHorizontalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRegisto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEsquerdoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelEsquerdoLayout.setVerticalGroup(
            painelEsquerdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEsquerdoLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegisto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        jSplitPane1.setLeftComponent(painelEsquerdo);

        painelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        painelPrincipal.setLayout(new java.awt.CardLayout());

        painelFaltas.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sobre o funcionário"));

        cbFuncionarioFalta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione um funcionário" }));
        cbFuncionarioFalta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFuncionarioFaltaActionPerformed(evt);
            }
        });

        cbMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccione o motivo da falta", "5 dias, por motivo de casamento", "5 dias, por motivo de falecimento de familiar do primeiro grau", "2 dias, por motivo de falecimento de familiar do segundo grau", "Impossibilidade de prestar trabalho por motivo de doença ou acidente", "Mãe ou pai acompanhantes dos seus próprios filhos ou outros menores sob a sua responsabilidade hospitalizados", "Aborto antes de 7 meses anteriores ao parto previsível", "Outras prévia ou posteriormente autorizadas pelo empregador" }));

        jLabel2.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel2.setText("Código: ");

        jLabel3.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel3.setText("Profissão:");

        chbFalta.setText("Atribuir Falta");
        chbFalta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbFaltaActionPerformed(evt);
            }
        });

        lbId.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        lbProfissao.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbFuncionarioFalta, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chbFalta)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFuncionarioFalta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(chbFalta)
                    .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel4.setText("Observações:");

        btnConfirmarFalta.setText("Confirmar");
        btnConfirmarFalta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarFaltaActionPerformed(evt);
            }
        });

        btnVoltarFaltas.setText("Voltar");
        btnVoltarFaltas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarFaltasActionPerformed(evt);
            }
        });

        txtOBS.setColumns(20);
        txtOBS.setRows(5);
        jScrollPane1.setViewportView(txtOBS);

        javax.swing.GroupLayout painelFaltasLayout = new javax.swing.GroupLayout(painelFaltas);
        painelFaltas.setLayout(painelFaltasLayout);
        painelFaltasLayout.setHorizontalGroup(
            painelFaltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelFaltasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFaltasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfirmarFalta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoltarFaltas, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelFaltasLayout.setVerticalGroup(
            painelFaltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFaltasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelFaltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelFaltasLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelFaltasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(painelFaltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltarFaltas)
                    .addComponent(btnConfirmarFalta))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        painelPrincipal.add(painelFaltas, "card2");

        painelFerias.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sobre o funcionário"));

        cbFuncionario.setFont(new java.awt.Font("Noto Sans", 0, 12)); // NOI18N
        cbFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione um funcionário" }));

        cbDias.setFont(new java.awt.Font("Noto Sans", 0, 12)); // NOI18N
        cbDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione o ano de trabalho", "Primeiro", "Segundo ", "Mais de dois anos" }));
        cbDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDiasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbDias, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel5.setText("Início: ");

        jLabel6.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel6.setText("Fim: ");

        obs.setColumns(20);
        obs.setRows(5);
        jScrollPane2.setViewportView(obs);

        dataInicio.setBackground(new java.awt.Color(255, 255, 255));

        dataFim.setBackground(new java.awt.Color(255, 255, 255));

        btnConfirmarFerias.setText("Confirmar");
        btnConfirmarFerias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarFeriasActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel7.setText("Observações: ");

        javax.swing.GroupLayout painelFeriasLayout = new javax.swing.GroupLayout(painelFerias);
        painelFerias.setLayout(painelFeriasLayout);
        painelFeriasLayout.setHorizontalGroup(
            painelFeriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelFeriasLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(painelFeriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(painelFeriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFeriasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfirmarFerias)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelFeriasLayout.setVerticalGroup(
            painelFeriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFeriasLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addGroup(painelFeriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFeriasLayout.createSequentialGroup()
                        .addGroup(painelFeriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dataInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(painelFeriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnConfirmarFerias)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        painelPrincipal.add(painelFerias, "card3");

        painelFuncionarios.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sobre o funcionário"));

        lbNome.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        lbNome.setText("Nome: ");

        jLabel9.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel9.setText("Apelido:");

        jLabel10.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel10.setText("Profissão: ");

        jButton8.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jButton8.setText("Demitir");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        lbNomeFunc.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        lbApelidoFunc.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        lbProfissaoFunc.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        chbFuncionariosDemitidos.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        chbFuncionariosDemitidos.setText("Funcionários Demitidos");
        chbFuncionariosDemitidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbFuncionariosDemitidosActionPerformed(evt);
            }
        });

        btnEfectividade.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        btnEfectividade.setText("Efectividade");
        btnEfectividade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEfectividadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNome, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbApelidoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNomeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbProfissaoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 410, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEfectividade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chbFuncionariosDemitidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(37, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNomeFunc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbApelidoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEfectividade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbProfissaoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chbFuncionariosDemitidos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE))))
        );

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

        tabelaFuncionario.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        tabelaFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaFuncionario);

        btnVoltarFuncionario.setText("Voltar");
        btnVoltarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelFuncionariosLayout = new javax.swing.GroupLayout(painelFuncionarios);
        painelFuncionarios.setLayout(painelFuncionariosLayout);
        painelFuncionariosLayout.setHorizontalGroup(
            painelFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelFuncionariosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVoltarFuncionario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelFuncionariosLayout.setVerticalGroup(
            painelFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFuncionariosLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoltarFuncionario)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        painelPrincipal.add(painelFuncionarios, "card4");

        painelPagamentoDeSalario.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel11.setText("Funcionário: ");

        cbFunc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccione um funcionário" }));
        cbFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFuncActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel12.setText("Salário: ");

        btnConf.setText("Confirmar");
        btnConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfActionPerformed(evt);
            }
        });

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lbSal.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(69, 69, 69)
                        .addComponent(lbSal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(230, 230, 230)
                        .addComponent(cbFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbSal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConf)
                    .addComponent(btnVoltar))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Salário", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        chbSubFer.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        chbSubFer.setText("Subsídio de férias");
        chbSubFer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbSubFerActionPerformed(evt);
            }
        });

        chbSubDecimoTer.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        chbSubDecimoTer.setText("13° salário");
        chbSubDecimoTer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbSubDecimoTerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(315, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbSubFer)
                    .addComponent(chbSubDecimoTer))
                .addContainerGap(328, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addComponent(chbSubFer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(chbSubDecimoTer)
                .addContainerGap(314, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Subsídios", jPanel5);

        javax.swing.GroupLayout painelPagamentoDeSalarioLayout = new javax.swing.GroupLayout(painelPagamentoDeSalario);
        painelPagamentoDeSalario.setLayout(painelPagamentoDeSalarioLayout);
        painelPagamentoDeSalarioLayout.setHorizontalGroup(
            painelPagamentoDeSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        painelPagamentoDeSalarioLayout.setVerticalGroup(
            painelPagamentoDeSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        painelPrincipal.add(painelPagamentoDeSalario, "card5");

        painelPerFerias.setBackground(new java.awt.Color(255, 255, 255));

        tabelaFerias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tabelaFerias);

        jButton10.setText("Voltar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelPerFeriasLayout = new javax.swing.GroupLayout(painelPerFerias);
        painelPerFerias.setLayout(painelPerFeriasLayout);
        painelPerFeriasLayout.setHorizontalGroup(
            painelPerFeriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
            .addGroup(painelPerFeriasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelPerFeriasLayout.setVerticalGroup(
            painelPerFeriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPerFeriasLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(0, 64, Short.MAX_VALUE))
        );

        painelPrincipal.add(painelPerFerias, "card6");

        painelSalario.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel13.setText("Contabilista");

        txtContabilista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContabilistaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel14.setText("Cozinheiro:");

        txtCozinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCozinheiroActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel15.setText("Electricista:");

        txtElectrecista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtElectrecistaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel16.setText("Frentista:");

        txtFrentista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFrentistaActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel17.setText("Guarda:");

        txtGuarda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuardaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel18.setText("Mecânico:");

        txtMecanico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMecanicoActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel21.setText("Subsídio de Alimentação:");

        txtSubAlim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubAlimActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel28.setText("MZN");

        jLabel22.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel22.setText("Subsídio de Transporte:");

        txtSubTransp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTranspActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel29.setText("MZN");

        jLabel23.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel23.setText("Bónus:");

        txtBonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBonusActionPerformed(evt);
            }
        });

        jLabel24.setText("MZN");

        jLabel19.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel19.setText("Motorista:");

        txtMotorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMotoristaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel20.setText("Serrelheiro:");

        txtSerrelheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerrelheiroActionPerformed(evt);
            }
        });

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnVoltar1.setText("Voltar");
        btnVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltar1ActionPerformed(evt);
            }
        });

        btnEditarSal.setText("Actualizar");
        btnEditarSal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarSalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelSalarioLayout = new javax.swing.GroupLayout(painelSalario);
        painelSalario.setLayout(painelSalarioLayout);
        painelSalarioLayout.setHorizontalGroup(
            painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSalarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelSalarioLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCozinheiro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(txtContabilista, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGuarda, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFrentista, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMecanico, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(txtMotorista)
                    .addComponent(txtSerrelheiro)
                    .addComponent(txtElectrecista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSubAlim, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtBonus, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addComponent(txtSubTransp)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(20, 20, 20))
            .addGroup(painelSalarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfirmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditarSal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelSalarioLayout.setVerticalGroup(
            painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelSalarioLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13)
                        .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtSubAlim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)))
                    .addGroup(painelSalarioLayout.createSequentialGroup()
                        .addComponent(txtContabilista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtCozinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txtSubTransp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelSalarioLayout.createSequentialGroup()
                        .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtElectrecista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFrentista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGuarda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSerrelheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(81, Short.MAX_VALUE))
                    .addGroup(painelSalarioLayout.createSequentialGroup()
                        .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(painelSalarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditarSal)
                            .addComponent(btnConfirmar)
                            .addComponent(btnVoltar1))
                        .addGap(29, 29, 29))))
        );

        painelPrincipal.add(painelSalario, "card7");

        painelRegisto.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel43.setText("Nome:");

        jLabel46.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel46.setText("NUIT:");

        jLabel47.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel47.setText("Data de Nascimento:");

        jLabel48.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel48.setText("Endereço:");

        jLabel49.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel49.setText("E-mail:");

        jLabel50.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel50.setText("Data de Ingresso:");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtNUIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNUITActionPerformed(evt);
            }
        });

        dcNascimento.setBackground(new java.awt.Color(255, 255, 255));

        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        dcIngresso.setBackground(new java.awt.Color(255, 255, 255));

        jLabel51.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel51.setText("Apelido:");

        jLabel54.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel54.setText("BI:");

        jLabel55.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel55.setText("Género:");

        jLabel56.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel56.setText("Número de Telefone:");

        jLabel57.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel57.setText("Profissão:");

        jLabel58.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel58.setText("Fim do Contracto:");

        txtApelido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApelidoActionPerformed(evt);
            }
        });

        txtBI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBIActionPerformed(evt);
            }
        });

        cbGenero.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Feminino", "Masculino" }));
        cbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGeneroActionPerformed(evt);
            }
        });

        txtNumeroTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroTelefoneActionPerformed(evt);
            }
        });

        cbProfissao.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        cbProfissao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Contabilista", "Cozinheiro", "Electricista", "Frentista", "Guarda", "Mecânico", "Motorista", "Serrelheiro" }));
        cbProfissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProfissaoActionPerformed(evt);
            }
        });

        dcFimContracto.setBackground(new java.awt.Color(255, 255, 255));

        btnConfReg.setText("Confirmar");
        btnConfReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfRegActionPerformed(evt);
            }
        });

        btnVoltarReg.setText("Voltar");
        btnVoltarReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarRegActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel30.setText("Nível Académico:");

        cbNvlAcademico.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        cbNvlAcademico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Básico", "Médio", "Superior" }));
        cbNvlAcademico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNvlAcademicoActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel31.setText("Instituição Académica:");

        cbFuncionarioReg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "funcionário" }));
        cbFuncionarioReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFuncionarioRegActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNome)
                    .addComponent(txtNUIT)
                    .addComponent(dcNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(txtEndereco)
                    .addComponent(txtEmail)
                    .addComponent(dcIngresso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbNvlAcademico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtApelido)
                        .addComponent(txtBI)
                        .addComponent(cbGenero, 0, 128, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtInstit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbProfissao, javax.swing.GroupLayout.Alignment.LEADING, 0, 128, Short.MAX_VALUE)
                                .addComponent(txtNumeroTelefone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                .addComponent(dcFimContracto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(2, 2, 2)))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbFuncionarioReg, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnConfReg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVoltarReg, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(cbFuncionarioReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(txtApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtNUIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(txtBI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel48)
                                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel56)
                                .addComponent(txtNumeroTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57)
                            .addComponent(cbProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50)
                                    .addComponent(dcIngresso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(cbNvlAcademico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31)
                                    .addComponent(txtInstit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(dcFimContracto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel58)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConfReg)
                            .addComponent(btnActualizar)
                            .addComponent(btnVoltarReg))))
                .addGap(76, 76, 76))
        );

        jTabbedPane2.addTab("Dados Pessoais", jPanel8);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        rbCompetente.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        rbCompetente.setText("Competente");
        rbCompetente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCompetenteActionPerformed(evt);
            }
        });

        rbDedicado.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        rbDedicado.setText("Dedicado");
        rbDedicado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDedicadoActionPerformed(evt);
            }
        });

        rbDisciplinado.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        rbDisciplinado.setText("Disciplinado");
        rbDisciplinado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDisciplinadoActionPerformed(evt);
            }
        });

        rbFlexivel.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        rbFlexivel.setText("Flexível");
        rbFlexivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFlexivelActionPerformed(evt);
            }
        });

        rbFormacao.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        rbFormacao.setText("Formação Adequada ao Cargo");
        rbFormacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFormacaoActionPerformed(evt);
            }
        });

        cbTipoAdmissao.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        cbTipoAdmissao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Interna", "Concurso Público" }));
        cbTipoAdmissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoAdmissaoActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel25.setText("Tipo de Admissão:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbDedicado)
                            .addComponent(rbCompetente)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addComponent(cbTipoAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(210, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbFlexivel)
                            .addComponent(rbDisciplinado)
                            .addComponent(rbFormacao))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(rbCompetente)
                .addGap(38, 38, 38)
                .addComponent(rbDedicado)
                .addGap(46, 46, 46)
                .addComponent(rbDisciplinado)
                .addGap(45, 45, 45)
                .addComponent(rbFlexivel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(rbFormacao)
                .addGap(42, 42, 42)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addContainerGap(215, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Efectividade", jPanel7);

        javax.swing.GroupLayout painelRegistoLayout = new javax.swing.GroupLayout(painelRegisto);
        painelRegisto.setLayout(painelRegistoLayout);
        painelRegistoLayout.setHorizontalGroup(
            painelRegistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRegistoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        painelRegistoLayout.setVerticalGroup(
            painelRegistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelRegistoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        painelPrincipal.add(painelRegisto, "card8");

        painelInicioo.setBackground(new java.awt.Color(255, 255, 255));

        maisFunc.setText("Mais dados");
        maisFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maisFuncActionPerformed(evt);
            }
        });

        maisFer.setText("Mais dados");
        maisFer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maisFerActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel26.setText("Funcionário");

        jLabel27.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel27.setText("Férias");

        tabelaDemoFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        painelDemoFunc.setViewportView(tabelaDemoFuncionario);

        tabelaDemoFer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tabelaDemoFer);

        javax.swing.GroupLayout painelIniciooLayout = new javax.swing.GroupLayout(painelInicioo);
        painelInicioo.setLayout(painelIniciooLayout);
        painelIniciooLayout.setHorizontalGroup(
            painelIniciooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelIniciooLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelIniciooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelDemoFunc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                    .addComponent(jScrollPane8)
                    .addGroup(painelIniciooLayout.createSequentialGroup()
                        .addGroup(painelIniciooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelIniciooLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(painelIniciooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maisFer, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maisFunc, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        painelIniciooLayout.setVerticalGroup(
            painelIniciooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelIniciooLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelDemoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(maisFunc)
                .addGap(20, 20, 20)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(maisFer)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        painelPrincipal.add(painelInicioo, "card9");

        painelRegistoo.setBackground(new java.awt.Color(255, 255, 255));

        btnFaltas.setBackground(new java.awt.Color(0, 153, 204));
        btnFaltas.setFont(new java.awt.Font("Clear Sans", 1, 14)); // NOI18N
        btnFaltas.setForeground(new java.awt.Color(255, 255, 255));
        btnFaltas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/Faltas.png"))); // NOI18N
        btnFaltas.setText("Faltas");
        btnFaltas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFaltas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFaltas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFaltasActionPerformed(evt);
            }
        });

        btnSalario.setBackground(new java.awt.Color(0, 153, 204));
        btnSalario.setFont(new java.awt.Font("Clear Sans", 1, 14)); // NOI18N
        btnSalario.setForeground(new java.awt.Color(255, 255, 255));
        btnSalario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/Salario.png"))); // NOI18N
        btnSalario.setText("Salário");
        btnSalario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalarioActionPerformed(evt);
            }
        });

        btnRegFunc.setBackground(new java.awt.Color(0, 153, 204));
        btnRegFunc.setFont(new java.awt.Font("Clear Sans", 1, 14)); // NOI18N
        btnRegFunc.setForeground(new java.awt.Color(255, 255, 255));
        btnRegFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/Funcionarios.png"))); // NOI18N
        btnRegFunc.setText("Funcionário");
        btnRegFunc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegFunc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegFuncActionPerformed(evt);
            }
        });

        btnDefSalario.setBackground(new java.awt.Color(0, 153, 204));
        btnDefSalario.setFont(new java.awt.Font("Clear Sans", 1, 14)); // NOI18N
        btnDefSalario.setForeground(new java.awt.Color(255, 255, 255));
        btnDefSalario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/Definir Salario.png"))); // NOI18N
        btnDefSalario.setText("Definir Salário");
        btnDefSalario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDefSalario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDefSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefSalarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelRegistooLayout = new javax.swing.GroupLayout(painelRegistoo);
        painelRegistoo.setLayout(painelRegistooLayout);
        painelRegistooLayout.setHorizontalGroup(
            painelRegistooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelRegistooLayout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addGroup(painelRegistooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFaltas, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(painelRegistooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegFunc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDefSalario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        painelRegistooLayout.setVerticalGroup(
            painelRegistooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRegistooLayout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addGroup(painelRegistooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFaltas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addGroup(painelRegistooLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDefSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        painelPrincipal.add(painelRegistoo, "card10");

        painelEfectividade.setBackground(new java.awt.Color(255, 255, 255));

        cbEfect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione um funcionário" }));
        cbEfect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEfectActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel8.setText("Competente:");

        jLabel32.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel32.setText("Dedicado:");

        jLabel33.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel33.setText("Disciplinado:");

        jLabel34.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel34.setText("Flexível:");

        jLabel35.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel35.setText("Formação Adequada ao cargo:");

        jLabel36.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N
        jLabel36.setText("Tipo de Admissão:");

        lbCompetente.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        lbDedicado.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        lbDisciplinado.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        lbFlexivel.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        lbFormacaoAd.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        lbTipoAdmissao.setFont(new java.awt.Font("Clear Sans", 0, 14)); // NOI18N

        btnVoltarEfect.setText("Voltar");
        btnVoltarEfect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarEfectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelEfectividadeLayout = new javax.swing.GroupLayout(painelEfectividade);
        painelEfectividade.setLayout(painelEfectividadeLayout);
        painelEfectividadeLayout.setHorizontalGroup(
            painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEfectividadeLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addGroup(painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCompetente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDedicado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDisciplinado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFlexivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFormacaoAd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTipoAdmissao, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addContainerGap(186, Short.MAX_VALUE))
            .addGroup(painelEfectividadeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEfect, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltarEfect))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelEfectividadeLayout.setVerticalGroup(
            painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEfectividadeLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(cbEfect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbCompetente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(lbDedicado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lbDisciplinado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lbFlexivel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lbFormacaoAd, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(painelEfectividadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lbTipoAdmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnVoltarEfect)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        painelPrincipal.add(painelEfectividade, "card11");

        jSplitPane1.setRightComponent(painelPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(983, 659));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
         //DefaultTableModel modelo = new DefaultTableModel();
        
        //tabelaDemoFer.setModel(modelo);
        
        cardLayout.show(painelPrincipal, "card9");

        btnInicio.setBackground(new Color(0, 153, 153));
        btnAgenda.setBackground(new Color(0, 153, 255));

        btnRegisto.setBackground(new Color(0, 153, 255));

        btnRelatorio.setBackground(new Color(0, 153, 255));
        
        listarFuncionarioDemo(CtrlFuncionario.getTodosDAOdecrescente());
        listarFeriasDemo(CtrlFerias.getTodosDAOdecrescente());
        listarFuncionario(CtrlFuncionario.getTodosDAO());
        listarFerias(CtrlFerias.getTodosDAO());

    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnRegistoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistoActionPerformed

        cardLayout.show(painelPrincipal, "card10");

        btnInicio.setBackground(new Color(0, 153, 255));
        btnRegisto.setBackground(new Color(0, 153, 153));

        btnAgenda.setBackground(new Color(0, 153, 255));

        btnRelatorio.setBackground(new Color(0, 153, 255));


    }//GEN-LAST:event_btnRegistoActionPerformed

    private void btnAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendaActionPerformed

        cardLayout.show(painelPrincipal, "card3");
         btnConfirmarFerias.setEnabled(false);

        btnInicio.setBackground(new Color(0, 153, 255));

        btnRegisto.setBackground(new Color(0, 153, 255));

        btnRelatorio.setBackground(new Color(0, 153, 255));

        btnAgenda.setBackground(new Color(0, 153, 153));

    }//GEN-LAST:event_btnAgendaActionPerformed

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed

        btnInicio.setBackground(new Color(0, 153, 255));

        btnRegisto.setBackground(new Color(0, 153, 255));

        btnAgenda.setBackground(new Color(0, 153, 255));

        btnRelatorio.setBackground(new Color(0, 153, 255));
        
        
        CtrlFerias dao = new CtrlFerias();
        
        //try {
            //Trabalhando com itext 4.2.1

           // Image img = Image.getInstance("imagem/TheClik.svg.png");
            
       // } catch (BadElementException | IOException ex) {
         //   Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
       // }
        Document document = new Document();
        

        String documento = "Relatório.pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(documento));

            Paragraph par = new Paragraph("Lista de Funcionários", 
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD,24,Font.BOLD,
                                                                    new Color(255,102,0)));
            
            
            par.setAlignment(1);

            document.open();
            document.add(par);
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph(new Date().toString()));
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            
            PdfPTable tabela = new PdfPTable(6);
            PdfPCell cabecalho = new PdfPCell(new Paragraph("Férias Agendadas",FontFactory.getFont(FontFactory.COURIER,14,
                                                                    new Color(255,255,255))));
            cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
            cabecalho.setBorder(PdfPCell.NO_BORDER);
            cabecalho.setBackgroundColor(new Color(0, 153, 204));
            cabecalho.setColspan(6);
            
            
            tabela.addCell(cabecalho);
            tabela.addCell("Nome");
            tabela.addCell("Apelido");
            tabela.addCell("Início");
            tabela.addCell("Fim");
            tabela.addCell("Dias");
            tabela.addCell("Observação");
            
            
              for (Ferias fer : dao.listarFer("")) {
                tabela.addCell(fer.getNome());
                tabela.addCell(fer.getApelido());
                tabela.addCell(String.valueOf(fer.getDataInicio()));
                tabela.addCell(String.valueOf(fer.getDataFim()));
                tabela.addCell(String.valueOf(fer.getDiasDeFerias()));
                tabela.addCell(fer.getObservacoes());

            
        }

            //  Image imagem =
            //   Image imagem = Image.getInstance("TheClikLogo.png");
            // imagem.scalePercent(50);
           

            
            
            
//                    document.add(new Paragraph("Trabalhando com itext"));
//                    document.add(new Paragraph("&#128526"));
//                    //document.add(new );
            document.add(tabela);

        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File(documento));
        } catch (IOException ex) {
            System.out.println("Erro: " + ex);
        }


    }//GEN-LAST:event_btnRelatorioActionPerformed

    private void btnInicioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseReleased

        // cardLayout.show(painelPrincipal, "card8");
        btnInicio.setBackground(new Color(0, 153, 153));

        btnAgenda.setBackground(new Color(0, 153, 255));

        btnRegisto.setBackground(new Color(0, 153, 255));

        btnRelatorio.setBackground(new Color(0, 153, 255));


    }//GEN-LAST:event_btnInicioMouseReleased

    private void btnVoltarFaltasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarFaltasActionPerformed

        cardLayout.show(painelPrincipal, "card10");

    }//GEN-LAST:event_btnVoltarFaltasActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    
            remover();
            
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfActionPerformed
        CtrlSalario daoSal = new CtrlSalario();
        CtrlFuncionario dao = new CtrlFuncionario();
        CtrlRemuneracao daoRem = new CtrlRemuneracao();

        Remuneracao remuneracao = new Remuneracao();
        Funcionario funcionario = new Funcionario();
        
        Funcionario func = (Funcionario) cbFunc.getSelectedItem();
        
        funcionario.setId(func.getId());
        funcionario.setNome(func.getNome());
        funcionario.setApelido(func.getApelido());
        funcionario.setNuit(func.getNuit());
        funcionario.setDataNascimento(func.getDataNascimento());
        funcionario.setEndereco(func.getEndereco());
        funcionario.setEmail(func.getEmail());
        funcionario.setDataIngresso(func.getDataIngresso());
        funcionario.setDataFimDoContracto(func.getDataFimDoContracto());
        funcionario.setBi(func.getBi());
        funcionario.setGenero(func.getGenero());
        funcionario.setNumeroTelefone(func.getNumeroTelefone());
        funcionario.setProfissao(func.getProfissao());
        funcionario.setNivelAcademico(func.getNivelAcademico());
        funcionario.setInstituicao(func.getInstituicao());
        funcionario.setEstado(func.getEstado());
        
        funcionario.setCompetente(func.getCompetente());
        funcionario.setDedicado(func.getDedicado());
        funcionario.setDisciplinado(func.getDisciplinado());
        funcionario.setFlexivel(func.getFlexivel());
        funcionario.setFormacaoAdequadaAoCargo(func.getFormacaoAdequadaAoCargo());
        funcionario.setTipoDeAdmissao(func.getTipoDeAdmissao());

        
        // Pegando dados da base de dados para o programa
            for (Remuneracao rem : daoRem.remunn("")) {

                remuneracao.setSubsidioDeAlimentacao(rem.getSubsidioDeAlimentacao());
                remuneracao.setSubsidioDeTransporte(rem.getSubsidioDeTransporte());
                remuneracao.setBonus(rem.getBonus());

            

        }

            
        // Pegando dados da base de dados para o programa
        for (Salario sa : daoSal.salarioo("")) {

            if (func.getProfissao().equals("Contabilista")) {
                funcionario.setSalarioBase(sa.getContabilista());
            }
            

            if (func.getProfissao().equals("Cozinheiro")) {
                funcionario.setSalarioBase(sa.getCozinheiro());
            }
                
            if (func.getProfissao().equals("Electricista")) {
                funcionario.setSalarioBase(sa.getElectricista());
            }
                
            
            if (func.getProfissao().equals("Frentista")) {
                funcionario.setSalarioBase(sa.getFrentista());
            }
                    
                    
            if (func.getProfissao().equals("Guarda")) {
                funcionario.setSalarioBase(sa.getGuarda());
            }
                        
            if (func.getProfissao().equals("Mecânico")) {
                funcionario.setSalarioBase(sa.getMecanico());
            }
                            
            if (func.getProfissao().equals("Motorista")) {
                funcionario.setSalarioBase(sa.getMotorista());
            }
                                
            if (func.getProfissao().equals("Serrelheiro")) {
                funcionario.setSalarioBase(sa.getSerrelheiro());
            }

        }
        
        double funcionarioSal = funcionario.getSalarioBase();
        
       
            //Atribuindo salario base e subsídios
        if (!chbSubFer.isSelected() && !chbSubDecimoTer.isSelected()){
            
        double salarioTotal =  funcionarioSal
                             + remuneracao.getSubsidioDeAlimentacao()
                             + remuneracao.getSubsidioDeTransporte()
                             + remuneracao.getBonus();
        
        funcionario.setSalarioBase(salarioTotal);
        
        }
        

        //Atribuindo o décimo terceiro salário
        if (chbSubDecimoTer.isSelected()){
            double salarioTotal =  2*funcionarioSal
                                 + remuneracao.getSubsidioDeAlimentacao()
                                 + remuneracao.getSubsidioDeTransporte()
                                 + remuneracao.getBonus();
            
            funcionario.setSalarioBase(salarioTotal);
            
        }
        
        
        //Atribuindo o subsídio de férias
        if (chbSubFer.isSelected()){
            
            funcionario.setSalarioBase(2*funcionarioSal);
        }
        
        
        lbSal.setText(String.valueOf(funcionario.getSalarioBase()).format("%.2f", funcionario.getSalarioBase()));
        
        
        dao.Actualizar(funcionario);
    }//GEN-LAST:event_btnConfActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed

        cardLayout.show(painelPrincipal, "card10");

    }//GEN-LAST:event_btnVoltarActionPerformed

    private void txtBonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBonusActionPerformed
        btnConfirmar.requestFocus();
    }//GEN-LAST:event_txtBonusActionPerformed

    private void txtContabilistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContabilistaActionPerformed
        txtCozinheiro.requestFocus();
    }//GEN-LAST:event_txtContabilistaActionPerformed

    private void txtSubAlimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubAlimActionPerformed
        txtSubTransp.requestFocus();
    }//GEN-LAST:event_txtSubAlimActionPerformed

    private void txtSerrelheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerrelheiroActionPerformed
        txtSubAlim.requestFocus();
    }//GEN-LAST:event_txtSerrelheiroActionPerformed

    private void txtMotoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMotoristaActionPerformed
        txtSerrelheiro.requestFocus();
    }//GEN-LAST:event_txtMotoristaActionPerformed

    private void txtMecanicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMecanicoActionPerformed
        txtMotorista.requestFocus();
    }//GEN-LAST:event_txtMecanicoActionPerformed

    private void txtGuardaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuardaActionPerformed
        txtMecanico.requestFocus();
    }//GEN-LAST:event_txtGuardaActionPerformed

    private void txtFrentistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFrentistaActionPerformed
        txtGuarda.requestFocus();
    }//GEN-LAST:event_txtFrentistaActionPerformed

    private void txtElectrecistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtElectrecistaActionPerformed
        txtFrentista.requestFocus();
    }//GEN-LAST:event_txtElectrecistaActionPerformed

    private void txtCozinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCozinheiroActionPerformed
        txtElectrecista.requestFocus();
    }//GEN-LAST:event_txtCozinheiroActionPerformed

    private void txtSubTranspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTranspActionPerformed
        txtBonus.requestFocus();
    }//GEN-LAST:event_txtSubTranspActionPerformed

    private void btnVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar1ActionPerformed

        cardLayout.show(painelPrincipal, "card10");

    }//GEN-LAST:event_btnVoltar1ActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        CtrlSalario ctrlSal = new CtrlSalario();
        CtrlRemuneracao ctrlRem = new CtrlRemuneracao();
        
        Salario sal = new Salario();
        Remuneracao rem = new Remuneracao();

        sal.setContabilista(Double.valueOf(txtContabilista.getText().replaceAll(",", ".")));

        sal.setCozinheiro(Double.valueOf(txtCozinheiro.getText().replaceAll(",", ".")));

        sal.setElectricista(Double.valueOf(txtElectrecista.getText().replaceAll(",", ".")));

        sal.setFrentista(Double.valueOf(txtFrentista.getText().replaceAll(",", ".")));

        sal.setGuarda(Double.valueOf(txtGuarda.getText().replaceAll(",", ".")));

        sal.setMecanico(Double.valueOf(txtMecanico.getText().replaceAll(",", ".")));

        sal.setMotorista(Double.valueOf(txtMotorista.getText().replaceAll(",", ".")));

        sal.setSerrelheiro(Double.valueOf(txtSerrelheiro.getText().replaceAll(",", ".")));

        rem.setBonus(Double.valueOf(txtBonus.getText().replaceAll(",", ".")));
        
        rem.setSubsidioDeAlimentacao(Double.valueOf(txtSubAlim.getText().replaceAll(",", ".")));
       
        rem.setSubsidioDeTransporte(Double.valueOf(txtSubTransp.getText().replaceAll(",", ".")));
        
        ctrlSal.gravarSalario(sal);
        ctrlRem.gravarRemuneracao(rem);
        

    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        TelaPrincipal tela = new TelaPrincipal();
        int resposta = JOptionPane.showConfirmDialog(null, "Realmente deseja sair?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {

            new Login().setVisible(true);
            dispose();
        } else {
            tela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }


    }//GEN-LAST:event_btnSairActionPerformed

    private void rbCompetenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCompetenteActionPerformed
        Funcionario funcionario = new Funcionario();
        
        if (rbCompetente.isSelected())
            funcionario.setCompetente("Sim");
        else 
            funcionario.setCompetente("Não");
        
            
    }//GEN-LAST:event_rbCompetenteActionPerformed

    private void rbDedicadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDedicadoActionPerformed
           Funcionario funcionario = new Funcionario();
        
           if (rbDedicado.isSelected())
               funcionario.setDedicado("Sim");
           else
               funcionario.setDedicado("Não");
    }//GEN-LAST:event_rbDedicadoActionPerformed

    private void rbDisciplinadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDisciplinadoActionPerformed
            Funcionario funcionario = new Funcionario();
        
           if (rbDisciplinado.isSelected())
               funcionario.setDisciplinado("Sim");
           else
               funcionario.setDisciplinado("Não");
    }//GEN-LAST:event_rbDisciplinadoActionPerformed

    private void rbFlexivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFlexivelActionPerformed
            Funcionario funcionario = new Funcionario();
        
           if (rbFlexivel.isSelected())
               funcionario.setFlexivel("Sim");
           else
               funcionario.setFlexivel("Não");
    }//GEN-LAST:event_rbFlexivelActionPerformed

    private void rbFormacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFormacaoActionPerformed
            Funcionario funcionario = new Funcionario();
        
           if (rbFormacao.isSelected())
               funcionario.setFormacaoAdequadaAoCargo("Sim");
           else
               funcionario.setFormacaoAdequadaAoCargo("Não");
    }//GEN-LAST:event_rbFormacaoActionPerformed

    private void cbTipoAdmissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoAdmissaoActionPerformed
            Funcionario funcionario = new Funcionario();
        
            funcionario.setTipoDeAdmissao(String.valueOf(cbTipoAdmissao.getSelectedItem()));

    }//GEN-LAST:event_cbTipoAdmissaoActionPerformed

    private void btnFaltasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFaltasActionPerformed

        cardLayout.show(painelPrincipal, "card2");

    }//GEN-LAST:event_btnFaltasActionPerformed

    private void btnRegFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegFuncActionPerformed

        cardLayout.show(painelPrincipal, "card8");


    }//GEN-LAST:event_btnRegFuncActionPerformed

    private void btnSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalarioActionPerformed

        cardLayout.show(painelPrincipal, "card5");

    }//GEN-LAST:event_btnSalarioActionPerformed

    private void btnDefSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefSalarioActionPerformed

        cardLayout.show(painelPrincipal, "card7");

    }//GEN-LAST:event_btnDefSalarioActionPerformed

    private void btnVoltarRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarRegActionPerformed

        cardLayout.show(painelPrincipal, "card10");

    }//GEN-LAST:event_btnVoltarRegActionPerformed

    private void btnConfirmarFeriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarFeriasActionPerformed
        Ferias fer = new Ferias();
        CtrlFerias ctrlFerias = new CtrlFerias();
        //DaoGenerico<Ferias> dao = new DaoGenerico<>();

        fer.setDataInicio(dataInicio.getDate());
        fer.setDataFim(dataFim.getDate());
        fer.setObservacoes(obs.getText());

        if (cbDias.getSelectedItem().equals("Primeiro")) {
            fer.setDiasDeFerias(12);
        } else if (cbDias.getSelectedItem().equals("Segundo")) {
            fer.setDiasDeFerias(24);
        } else {
            fer.setDiasDeFerias(30);
        }

        Funcionario func = (Funcionario) cbFuncionario.getSelectedItem();
        
        fer.setNome(func.getNome());
        fer.setApelido(func.getApelido());
         
        ctrlFerias.gravarFerias(fer);
     
        listarFeriasDemo(CtrlFerias.getTodosDAOdecrescente());
        listarFuncionarioDemo(CtrlFuncionario.getTodosDAOdecrescente());
       

       
    }//GEN-LAST:event_btnConfirmarFeriasActionPerformed

    private void maisFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisFuncActionPerformed

        cardLayout.show(painelPrincipal, "card4");
        listarFuncionarioActivo();
        
    }//GEN-LAST:event_maisFuncActionPerformed

    private void maisFerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisFerActionPerformed

        cardLayout.show(painelPrincipal, "card6");
    }//GEN-LAST:event_maisFerActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        cardLayout.show(painelPrincipal, "card9");

    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnVoltarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarFuncionarioActionPerformed

        cardLayout.show(painelPrincipal, "card9");

    }//GEN-LAST:event_btnVoltarFuncionarioActionPerformed

    private void btnConfRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfRegActionPerformed

        //camposRegisto();
        CtrlSalario daoSal = new CtrlSalario();
        CtrlFuncionario dao = new CtrlFuncionario();
        Salario salario = new Salario();
        Funcionario func = new Funcionario();
       // Funcionario fun = (Funcionario) cbProfissao.getSelectedItem();
        Salario sal = new Salario();

        func.setNome(txtNome.getText());
        func.setApelido(txtApelido.getText());
        func.setNuit(Integer.valueOf(txtNUIT.getText()));
        func.setDataNascimento(dcNascimento.getDate());
        func.setEndereco(txtEndereco.getText());
        func.setEmail(txtEmail.getText());
        func.setDataIngresso(dcIngresso.getDate());
        func.setDataFimDoContracto(dcFimContracto.getDate());
        func.setBi(txtBI.getText());
        func.setGenero((String) cbGenero.getSelectedItem().toString());
        func.setNumeroTelefone(txtNumeroTelefone.getText());
        func.setProfissao((String) cbProfissao.getSelectedItem());
        func.setNivelAcademico((String)cbNvlAcademico.getSelectedItem());
        func.setInstituicao((String) txtInstit.getText());
        func.setEstado("activo");
        

        if (rbCompetente.isSelected()) {
            func.setCompetente("Sim");
        }
        else 
            func.setCompetente("Não");

        if (rbDedicado.isSelected()) {
            func.setDedicado("Sim");
        }
        
        else
            func.setDedicado("Não");

        if (rbDisciplinado.isSelected()) {
            func.setDisciplinado("Sim");
        }
        
        else
            func.setDisciplinado("Não");

        if (rbFlexivel.isSelected()) {
            func.setFlexivel("Sim");
        }

        else
            func.setFlexivel("Não");
        
        if (rbFormacao.isSelected()) {
            func.setFormacaoAdequadaAoCargo("Sim");
        }
        
        else 
            func.setFormacaoAdequadaAoCargo("Não");

        func.setTipoDeAdmissao((String) cbTipoAdmissao.getSelectedItem());

        
        
         if (cbProfissao.getSelectedIndex() == 1) {

            for (Salario salar : daoSal.salarioo("")) {

                func.setSalarioBase(salar.getContabilista());

            }

        }

        if (cbProfissao.getSelectedIndex() == 2) {

            for (Salario salar : daoSal.salarioo("")) {

                func.setSalarioBase(salar.getCozinheiro());

            }

        }

        if (cbProfissao.getSelectedIndex() == 3) {

            for (Salario salar : daoSal.salarioo("")) {

                func.setSalarioBase(salar.getElectricista());

            }

        }

        if (cbProfissao.getSelectedIndex() == 4) {

            for (Salario salar : daoSal.salarioo("")) {

                func.setSalarioBase(salar.getFrentista());

            }

        }

        if (cbProfissao.getSelectedIndex() == 5) {

            for (Salario salar : daoSal.salarioo("")) {

                func.setSalarioBase(salar.getGuarda());

            }

        }

        if (cbProfissao.getSelectedIndex() == 6) {

            for (Salario salar : daoSal.salarioo("")) {

                func.setSalarioBase(salar.getMecanico());

            }

        }

        if (cbProfissao.getSelectedIndex() == 7) {

            for (Salario salar : daoSal.salarioo("")) {

                func.setSalarioBase(salar.getMotorista());

            }

        }

        if (cbProfissao.getSelectedIndex() == 8) {

            for (Salario salar : daoSal.salarioo("")) {

                func.setSalarioBase(salar.getSerrelheiro());

            }

        }
        
        txtNome.setText("");
        txtApelido.setText("");
        txtNUIT.setText("");
        txtEndereco.setText("");
        txtEmail.setText("");
        txtBI.setText("");
        txtNumeroTelefone.setText("");
        txtInstit.setText("");
             
        dao.gravarFuncionario(func);
         listarFeriasDemo(CtrlFerias.getTodosDAOdecrescente());
    }//GEN-LAST:event_btnConfRegActionPerformed

    private void btnConfirmarFaltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarFaltaActionPerformed
        Funcionario func = (Funcionario) cbFuncionarioFalta.getSelectedItem();
        Ferias fer = new Ferias();
        CtrlFuncionario dao = new CtrlFuncionario();
        
      
        if (chbFalta.isSelected()) {
            func.setNrFaltas(func.getNrFaltas() + 1);
        } 
       
        else{
             func.setNrFaltas(func.getNrFaltas() - 1);
             //fer.setObservacoes();
        }
           
        
        dao.Actualizar(func);
    }//GEN-LAST:event_btnConfirmarFaltaActionPerformed

    
    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtNUITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNUITActionPerformed
        dcNascimento.requestFocus();
    }//GEN-LAST:event_txtNUITActionPerformed

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        txtEmail.requestFocus();
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        dcIngresso.requestFocus();
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtApelidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApelidoActionPerformed
      
    }//GEN-LAST:event_txtApelidoActionPerformed

    private void txtBIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBIActionPerformed
        cbGenero.requestFocus();
    }//GEN-LAST:event_txtBIActionPerformed

    private void cbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGeneroActionPerformed
        txtNumeroTelefone.requestFocus();
        if (cbGenero.getSelectedIndex() == 0)
            btnConfReg.setSelected(false);
        else 
            btnConfReg.setSelected(true);
    }//GEN-LAST:event_cbGeneroActionPerformed

    private void txtNumeroTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroTelefoneActionPerformed
        cbProfissao.requestFocus();
    }//GEN-LAST:event_txtNumeroTelefoneActionPerformed

    private void cbProfissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProfissaoActionPerformed
       Funcionario func = new Funcionario();
       Salario sal = new Salario();
        
        dcFimContracto.requestFocus();
        txtNumeroTelefone.requestFocus();
        
        if (cbProfissao.getSelectedIndex() == 0)
            btnConfReg.setSelected(false);
        else 
            btnConfReg.setSelected(true);
        
             if (cbProfissao.getSelectedIndex() == 0) {
            func.setSalarioBase(sal.getContabilista());
        }

        if (cbProfissao.getSelectedIndex() == 1) {
            func.setSalarioBase(sal.getCozinheiro());
        }

        if (cbProfissao.getSelectedIndex() == 2) {
            func.setSalarioBase(sal.getElectricista());
        }

        if (cbProfissao.getSelectedIndex() == 3) {
            func.setSalarioBase(sal.getFrentista());
        }

        if (cbProfissao.getSelectedIndex() == 4) {
            func.setSalarioBase(sal.getGuarda());
        }

        if (cbProfissao.getSelectedIndex() == 5) {
            func.setSalarioBase(sal.getMecanico());
        }

        if (cbProfissao.getSelectedIndex() == 6) {
            func.setSalarioBase(sal.getMotorista());
        }

        if (cbProfissao.getSelectedIndex() == 7) {
            func.setSalarioBase(sal.getSerrelheiro());
        }
        
    }//GEN-LAST:event_cbProfissaoActionPerformed

    private void chbFaltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbFaltaActionPerformed
      Funcionario func = new Funcionario();
        
        if (chbFalta.isSelected()){
	cbMotivo.setEnabled(false);
	txtOBS.setEnabled(false);
        
        
        func.setNrFaltas(func.getNrFaltas()+1);
        
        }else{
            cbMotivo.setEnabled(true);
            txtOBS.setEnabled(true);
        }
        
    }//GEN-LAST:event_chbFaltaActionPerformed

    private void cbDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDiasActionPerformed
       
        if (cbDias.getSelectedIndex() == 0 || cbFuncionario.getSelectedIndex() == 0)
            btnConfirmarFerias.setEnabled(false);
        else
            btnConfirmarFerias.setEnabled(true);
    }//GEN-LAST:event_cbDiasActionPerformed

    private void cbFuncionarioFaltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFuncionarioFaltaActionPerformed
        
        Funcionario func = (Funcionario) cbFuncionarioFalta.getSelectedItem();
        
        lbId.setText(String.valueOf(func.getId()));
        lbProfissao.setText(String.valueOf(func.getProfissao()));
        
        
        
        
        
    }//GEN-LAST:event_cbFuncionarioFaltaActionPerformed

    private void cbNvlAcademicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNvlAcademicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNvlAcademicoActionPerformed

    private void chbFuncionariosDemitidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbFuncionariosDemitidosActionPerformed
        
        if (chbFuncionariosDemitidos.isSelected())
               listarFuncionario(CtrlFuncionario.getTodosDAO());
            
            else
               listarFuncionarioActivo();
      
    }//GEN-LAST:event_chbFuncionariosDemitidosActionPerformed

    private void tabelaFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFuncionarioMouseClicked
            
     
        int linha = tabelaFuncionario.getSelectedRow();

        if (linha != -1) {
            
            lbNomeFunc.setText((String) tabelaFuncionario.getValueAt(linha, 1));
            lbApelidoFunc.setText((String) tabelaFuncionario.getValueAt(linha, 2));
            lbProfissaoFunc.setText((String) tabelaFuncionario.getValueAt(linha, 12));

    }
    }//GEN-LAST:event_tabelaFuncionarioMouseClicked

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane3MouseClicked

    private void cbFuncionarioRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFuncionarioRegActionPerformed
       
        Funcionario func = new Funcionario();
        Funcionario funcionario = (Funcionario) cbFuncionarioReg.getSelectedItem();

        func.setId(funcionario.getId());

        txtNome.setText(funcionario.getNome());

        txtApelido.setText(funcionario.getApelido());

        txtNUIT.setText(String.valueOf(funcionario.getNuit()));

        txtBI.setText(funcionario.getBi());

        txtEndereco.setText(funcionario.getEndereco());

        txtNumeroTelefone.setText(funcionario.getNumeroTelefone());

        txtEmail.setText(funcionario.getEmail());

        txtInstit.setText(funcionario.getInstituicao());
        
        
        
    }//GEN-LAST:event_cbFuncionarioRegActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
       
        
        cbFuncionarioReg.setEnabled(true);
        

        Funcionario funcionario = new Funcionario();
        
        Funcionario func = (Funcionario) cbFuncionarioReg.getSelectedItem();
        
        funcionario.setId(func.getId());
        txtNome.setText(String.valueOf(func.getNome()));
        
        txtApelido.setText(String.valueOf(func.getApelido()));
        
        txtNUIT.setText(String.valueOf(func.getNuit()));
        
        txtEndereco.setText(String.valueOf(func.getEndereco()));
        
        txtEmail.setText(String.valueOf(func.getEmail()));
        
        txtBI.setText(String.valueOf(func.getBi()));
        
        txtNumeroTelefone.setText(String.valueOf(func.getNumeroTelefone()));
        
        txtInstit.setText(String.valueOf(func.getInstituicao()));
        //funcionario.setEstado(func.getEstado());
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void cbFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFuncActionPerformed

    private void btnEditarSalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarSalActionPerformed
            
        CtrlRemuneracao daoRem = new CtrlRemuneracao();
        CtrlSalario daoSal = new CtrlSalario();
        
        
        Remuneracao remuneracao = new Remuneracao();
        Salario salario = new Salario();
        
          for (Remuneracao rem : daoRem.remunn("")) {

                remuneracao.setSubsidioDeAlimentacao(rem.getSubsidioDeAlimentacao());
                remuneracao.setSubsidioDeTransporte(rem.getSubsidioDeTransporte());
                remuneracao.setBonus(rem.getBonus());

        }
          
          
           for (Salario sal : daoSal.salarioo("")) {

                salario.setContabilista(sal.getContabilista());

                salario.setCozinheiro(sal.getCozinheiro());

                salario.setElectricista(sal.getElectricista());

                salario.setFrentista(sal.getFrentista());

                salario.setGuarda(sal.getGuarda());

                salario.setMecanico(sal.getMecanico());

                salario.setMotorista(sal.getMotorista());

                salario.setSerrelheiro(sal.getSerrelheiro());

        }
        
        
        
        txtContabilista.setText(String.valueOf(salario.getContabilista()).format("%.2f", salario.getContabilista()));

        txtCozinheiro.setText(String.valueOf(salario.getCozinheiro()).format("%.2f", salario.getCozinheiro()));

        txtElectrecista.setText(String.valueOf(salario.getElectricista()).format("%.2f", salario.getElectricista()));

        txtFrentista.setText(String.valueOf(salario.getFrentista()).format("%.2f", salario.getFrentista()));

        txtGuarda.setText(String.valueOf(salario.getGuarda()).format("%.2f", salario.getGuarda()));

        txtMecanico.setText(String.valueOf(salario.getMecanico()).format("%.2f", salario.getMecanico()));

        txtMotorista.setText(String.valueOf(salario.getMotorista()).format("%.2f", salario.getMotorista()));

        txtSerrelheiro.setText(String.valueOf(salario.getSerrelheiro()).format("%.2f", salario.getSerrelheiro()));

        txtBonus.setText(String.valueOf(remuneracao.getBonus()).format("%.2f", remuneracao.getBonus()));
        
        txtSubAlim.setText(String.valueOf(remuneracao.getSubsidioDeAlimentacao()).format("%.2f", remuneracao.getSubsidioDeAlimentacao()));
        
        txtSubTransp.setText(String.valueOf(remuneracao.getSubsidioDeTransporte()).format("%.2f", remuneracao.getSubsidioDeTransporte()));
        
    }//GEN-LAST:event_btnEditarSalActionPerformed

    private void btnEfectividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfectividadeActionPerformed
        
        cardLayout.show(painelPrincipal, "card11");
                
    }//GEN-LAST:event_btnEfectividadeActionPerformed

    private void btnVoltarEfectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarEfectActionPerformed
        
        cardLayout.show(painelPrincipal, "card4");
        
    }//GEN-LAST:event_btnVoltarEfectActionPerformed

    private void cbEfectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEfectActionPerformed
       
        Funcionario func = (Funcionario) cbEfect.getSelectedItem();
        
        
            lbCompetente.setText(String.valueOf(func.getCompetente()));
            lbDedicado.setText(String.valueOf(func.getDedicado()));
            lbDisciplinado.setText(String.valueOf(func.getDisciplinado()));
            lbFlexivel.setText(String.valueOf(func.getFlexivel()));
            lbFormacaoAd.setText(String.valueOf(func.getFormacaoAdequadaAoCargo()));
            lbTipoAdmissao.setText(String.valueOf(func.getTipoDeAdmissao()));
    }//GEN-LAST:event_cbEfectActionPerformed

    private void chbSubFerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbSubFerActionPerformed
        
        if (chbSubFer.isSelected() && chbSubDecimoTer.isSelected())
            chbSubDecimoTer.setSelected(false);
        
    }//GEN-LAST:event_chbSubFerActionPerformed

    private void chbSubDecimoTerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbSubDecimoTerActionPerformed
        
        if (chbSubDecimoTer.isSelected() && chbSubDecimoTer.isSelected())
            chbSubFer.setSelected(false);
        
    }//GEN-LAST:event_chbSubDecimoTerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                
                TelaPrincipal tela = new TelaPrincipal();
                tela.setVisible(true);

                tela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                tela.addWindowListener(new WindowAdapter() {

                    public void windowClosing(WindowEvent e) {

                        int resposta = JOptionPane.showConfirmDialog(null, "Realmente deseja encerrar o programa?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        } else {
                            tela.setVisible(true);
                        }
                    }
                });

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgenda;
    private javax.swing.JButton btnConf;
    private javax.swing.JButton btnConfReg;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnConfirmarFalta;
    private javax.swing.JButton btnConfirmarFerias;
    private javax.swing.JButton btnDefSalario;
    private javax.swing.JButton btnEditarSal;
    private javax.swing.JButton btnEfectividade;
    private javax.swing.JButton btnFaltas;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnRegFunc;
    private javax.swing.JButton btnRegisto;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalario;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnVoltar1;
    private javax.swing.JButton btnVoltarEfect;
    private javax.swing.JButton btnVoltarFaltas;
    private javax.swing.JButton btnVoltarFuncionario;
    private javax.swing.JButton btnVoltarReg;
    private javax.swing.JComboBox<String> cbDias;
    private javax.swing.JComboBox<Object> cbEfect;
    private javax.swing.JComboBox<Object> cbFunc;
    private javax.swing.JComboBox<Object> cbFuncionario;
    private javax.swing.JComboBox<Object> cbFuncionarioFalta;
    private javax.swing.JComboBox<Object> cbFuncionarioReg;
    private javax.swing.JComboBox<String> cbGenero;
    private javax.swing.JComboBox<String> cbMotivo;
    private javax.swing.JComboBox<String> cbNvlAcademico;
    private javax.swing.JComboBox<String> cbProfissao;
    private javax.swing.JComboBox<String> cbTipoAdmissao;
    private javax.swing.JCheckBox chbFalta;
    private javax.swing.JCheckBox chbFuncionariosDemitidos;
    private javax.swing.JCheckBox chbSubDecimoTer;
    private javax.swing.JCheckBox chbSubFer;
    private com.toedter.calendar.JDateChooser dataFim;
    private com.toedter.calendar.JDateChooser dataInicio;
    private com.toedter.calendar.JDateChooser dcFimContracto;
    private com.toedter.calendar.JDateChooser dcIngresso;
    private com.toedter.calendar.JDateChooser dcNascimento;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lbApelidoFunc;
    private javax.swing.JLabel lbCompetente;
    private javax.swing.JLabel lbDedicado;
    private javax.swing.JLabel lbDisciplinado;
    private javax.swing.JLabel lbFlexivel;
    private javax.swing.JLabel lbFormacaoAd;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNomeFunc;
    private javax.swing.JLabel lbProfissao;
    private javax.swing.JLabel lbProfissaoFunc;
    private javax.swing.JLabel lbSal;
    private javax.swing.JLabel lbTipoAdmissao;
    private javax.swing.JButton maisFer;
    private javax.swing.JButton maisFunc;
    private javax.swing.JTextArea obs;
    private javax.swing.JScrollPane painelDemoFunc;
    private javax.swing.JPanel painelEfectividade;
    private javax.swing.JPanel painelEsquerdo;
    private javax.swing.JPanel painelFaltas;
    private javax.swing.JPanel painelFerias;
    private javax.swing.JPanel painelFuncionarios;
    private javax.swing.JPanel painelInicioo;
    private javax.swing.JPanel painelPagamentoDeSalario;
    private javax.swing.JPanel painelPerFerias;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JPanel painelRegisto;
    private javax.swing.JPanel painelRegistoo;
    private javax.swing.JPanel painelSalario;
    private javax.swing.JRadioButton rbCompetente;
    private javax.swing.JRadioButton rbDedicado;
    private javax.swing.JRadioButton rbDisciplinado;
    private javax.swing.JRadioButton rbFlexivel;
    private javax.swing.JRadioButton rbFormacao;
    private javax.swing.JTable tabelaDemoFer;
    private javax.swing.JTable tabelaDemoFuncionario;
    private javax.swing.JTable tabelaFerias;
    private javax.swing.JTable tabelaFuncionario;
    private javax.swing.JTextField txtApelido;
    private javax.swing.JTextField txtBI;
    private javax.swing.JTextField txtBonus;
    private javax.swing.JTextField txtContabilista;
    private javax.swing.JTextField txtCozinheiro;
    private javax.swing.JTextField txtElectrecista;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtFrentista;
    private javax.swing.JTextField txtGuarda;
    private javax.swing.JTextField txtInstit;
    private javax.swing.JTextField txtMecanico;
    private javax.swing.JTextField txtMotorista;
    private javax.swing.JTextField txtNUIT;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumeroTelefone;
    private javax.swing.JTextArea txtOBS;
    private javax.swing.JTextField txtSerrelheiro;
    private javax.swing.JTextField txtSubAlim;
    private javax.swing.JTextField txtSubTransp;
    // End of variables declaration//GEN-END:variables

  
}
