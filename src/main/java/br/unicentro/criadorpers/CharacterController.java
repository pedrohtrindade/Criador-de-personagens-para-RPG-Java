package br.unicentro.criadorpers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CharacterController {
    @FXML private TextField nomeField;
    @FXML private Spinner<Integer> nivelSpinner;
    @FXML private Spinner<Integer> forcaSpinner;
    @FXML private Spinner<Integer> destrezaSpinner;
    @FXML private Spinner<Integer> constituicaoSpinner;
    @FXML private Spinner<Integer> inteligenciaSpinner;
    @FXML private Spinner<Integer> sabedoriaSpinner;
    @FXML private Spinner<Integer> carismaSpinner;

    @FXML private ComboBox<Race> racaCombo;
    @FXML private ComboBox<Classe> classeCombo;

    @FXML private TableView<Character> tabelaPersonagens;
    @FXML private TableColumn<Character, String> colNome;
    @FXML private TableColumn<Character, Integer> colNivel;
    @FXML private TableColumn<Character, String> colRaca;
    @FXML private TableColumn<Character, String> colClasse;
    @FXML private TableColumn<Character, Integer> colForca;
    @FXML private TableColumn<Character, Integer> colDestreza;
    @FXML private TableColumn<Character, Integer> colConstituicao;
    @FXML private TableColumn<Character, Integer> colInteligencia;
    @FXML private TableColumn<Character, Integer> colSabedoria;
    @FXML private TableColumn<Character, Integer> colCarisma;
    @FXML private TableColumn<Character, String> colHabilidades;
    @FXML private TableColumn<Character, String> colEquipamentos;

    @FXML private ListView<Equipamento> equipamentosDisponiveis;
    @FXML private ListView<Equipamento> equipamentosPersonagem;
    @FXML private ListView<Habilidade> habilidadesDisponiveis;
    @FXML private ListView<Habilidade> habilidadesPersonagem;

    @FXML
    public void initialize() {
        // Spinners
        nivelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));
        forcaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        destrezaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        constituicaoSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        inteligenciaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        sabedoriaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));
        carismaSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10));

        // Carregar combos
        racaCombo.setItems(FXCollections.observableArrayList(RaceDAO.listarRacas()));
        classeCombo.setItems(FXCollections.observableArrayList(ClassDAO.listarClasses()));

        // Carregar lista de equipamentos
        equipamentosDisponiveis.setItems(FXCollections.observableArrayList(EquipamentoDAO.listarEquipamentos()));
        equipamentosPersonagem.setItems(FXCollections.observableArrayList());

        // Carregar lista de habilidades
        habilidadesDisponiveis.setItems(FXCollections.observableArrayList(HabilidadeDAO.listarHabilidades()));
        habilidadesPersonagem.setItems(FXCollections.observableArrayList());

        // Configurar colunas da tabela
        colNome.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNome()));
        colNivel.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getNivel()));
        colRaca.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getRaca().getNome()));
        colClasse.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getClasse().getNome()));

        colForca.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getForca()));
        colDestreza.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getDestreza()));
        colConstituicao.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getConstituicao()));
        colInteligencia.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getInteligencia()));
        colSabedoria.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getSabedoria()));
        colCarisma.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getCarisma()));

        colHabilidades.setCellValueFactory(c -> {
            if (c.getValue().getHabilidades() != null && !c.getValue().getHabilidades().isEmpty()) {
                String habilidadesTexto = c.getValue().getHabilidades()
                        .stream()
                        .map(Habilidade::getNome)
                        .collect(java.util.stream.Collectors.joining(", "));
                return new javafx.beans.property.SimpleStringProperty(habilidadesTexto);
            } else {
                return new javafx.beans.property.SimpleStringProperty("");
            }
        });

        colEquipamentos.setCellValueFactory(c -> {
            if (c.getValue().getEquipamentos() != null && !c.getValue().getEquipamentos().isEmpty()) {
                String equipamentosTexto = c.getValue().getEquipamentos()
                        .stream()
                        .map(Equipamento::getNome)
                        .collect(java.util.stream.Collectors.joining(", "));
                return new javafx.beans.property.SimpleStringProperty(equipamentosTexto);
            } else {
                return new javafx.beans.property.SimpleStringProperty("");
            }
        });

        atualizarTabela();
    }

    @FXML
    public void salvarPersonagem() {
        String nome = nomeField.getText();
        int nivel = nivelSpinner.getValue();
        int forca = forcaSpinner.getValue();
        int destreza = destrezaSpinner.getValue();
        int constituicao = constituicaoSpinner.getValue();
        int inteligencia = inteligenciaSpinner.getValue();
        int sabedoria = sabedoriaSpinner.getValue();
        int carisma = carismaSpinner.getValue();
        Race r = racaCombo.getValue();
        Classe c = classeCombo.getValue();

        if (nome.isEmpty() || r == null || c == null) {
            new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!").show();
            return;
        }

        // Inserir personagem no banco
        int personagemId = CharacterDAO.inserirPersonagem(nome, nivel, forca, destreza, constituicao,
                inteligencia, sabedoria, carisma, r.getId(), c.getId());

        // Inserir equipamentos selecionados
        for (Equipamento eq : equipamentosPersonagem.getItems()) {
            CharacterDAO.adicionarEquipamento(personagemId, eq.getId());
        }

        // Inserir habilidades selecionadas
        for (Habilidade h : habilidadesPersonagem.getItems()) {
            CharacterDAO.adicionarHabilidade(personagemId, h.getId());
        }

        atualizarTabela();
        limparCampos();
    }

    @FXML
    private void deletarPersonagem() {
        Character selecionado = tabelaPersonagens.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            CharacterDAO.deletarPersonagem(selecionado.getId());
            atualizarTabela();
        } else {
            new Alert(Alert.AlertType.WARNING, "Selecione um personagem para deletar!").show();
        }
    }

    @FXML
    private void adicionarEquipamento() {
        Equipamento selecionado = equipamentosDisponiveis.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            equipamentosDisponiveis.getItems().remove(selecionado);
            equipamentosPersonagem.getItems().add(selecionado);
        }
    }

    @FXML
    private void removerEquipamento() {
        Equipamento selecionado = equipamentosPersonagem.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            equipamentosPersonagem.getItems().remove(selecionado);
            equipamentosDisponiveis.getItems().add(selecionado);
        }
    }

    @FXML
    private void adicionarHabilidade() {
        Habilidade selecionada = habilidadesDisponiveis.getSelectionModel().getSelectedItem();
        if (selecionada != null && !habilidadesPersonagem.getItems().contains(selecionada)) {
            habilidadesPersonagem.getItems().add(selecionada);
        }
    }

    @FXML
    private void removerHabilidade() {
        Habilidade selecionada = habilidadesPersonagem.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            habilidadesPersonagem.getItems().remove(selecionada);
        }
    }

    private void atualizarTabela() {
        tabelaPersonagens.setItems(FXCollections.observableArrayList(CharacterDAO.listarPersonagens()));
    }

    private void limparCampos() {
        nomeField.clear();
        nivelSpinner.getValueFactory().setValue(1);
        forcaSpinner.getValueFactory().setValue(10);
        destrezaSpinner.getValueFactory().setValue(10);
        constituicaoSpinner.getValueFactory().setValue(10);
        inteligenciaSpinner.getValueFactory().setValue(10);
        sabedoriaSpinner.getValueFactory().setValue(10);
        carismaSpinner.getValueFactory().setValue(10);
        racaCombo.getSelectionModel().clearSelection();
        classeCombo.getSelectionModel().clearSelection();

        equipamentosPersonagem.getItems().clear();
        equipamentosDisponiveis.setItems(FXCollections.observableArrayList(EquipamentoDAO.listarEquipamentos()));

        habilidadesPersonagem.getItems().clear();
        habilidadesDisponiveis.setItems(FXCollections.observableArrayList(HabilidadeDAO.listarHabilidades()));
    }
}
