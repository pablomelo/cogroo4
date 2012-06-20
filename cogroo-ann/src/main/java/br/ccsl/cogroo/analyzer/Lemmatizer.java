package br.ccsl.cogroo.analyzer;

import java.util.List;

import br.ccsl.cogroo.dictionary.LemmaDictionaryI;
import br.ccsl.cogroo.text.Document;
import br.ccsl.cogroo.text.Sentence;
import br.ccsl.cogroo.text.Token;

public class Lemmatizer implements AnalyzerI {
  private LemmaDictionaryI dict;

  public Lemmatizer(LemmaDictionaryI dict) {
    this.dict = dict;
  }
  public void analyze(Document document) {
    List<Sentence> sentences = document.getSentences();
    
    for (Sentence sentence : sentences) {
      List<Token> tokens = sentence.getTokens();
      
      for (int i = 0; i < tokens.size(); i++) {
        String tag = tokens.get(i).getPOSTag();
        String word = tokens.get(i).getLexeme();
        
        String[] lemmas = dict.getLemmas(word, tag);
        
        if (lemmas == null || lemmas.length == 0) {
          lemmas = dict.getLemmas(word.toLowerCase(), tag);
        }
        
        tokens.get(i).setLemmas(lemmas);
      }
    }
  }
}