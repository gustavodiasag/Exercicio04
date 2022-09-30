import com.azure.ai.textanalytics.*;
import com.azure.ai.textanalytics.models.DocumentSentiment;
import com.azure.ai.textanalytics.models.SentimentConfidenceScores;
import com.azure.core.credential.AzureKeyCredential;

public class TextAnalysis {
     
    public static void main(String[] args) {

    	TextAnalyticsClient client = new TextAnalyticsClientBuilder()
                                         .credential(new AzureKeyCredential("ca8de3c4ea004787a0ed003953975170"))
                                         .endpoint("https://textrecognitionjava.cognitiveservices.azure.com/")
                                         .buildClient();

        // Second verse of the song "Screen Shot" by Swans
        String document = "No pain, no death, no fear, no hate\n"
        		+ "No time, no now, no suffering\n"
        		+ "No touch, no loss, no hand, no sense\n"
        		+ "No wound, no waste, no lust, no fear\n"
        		+ "No mind, no greed, no suffering\n"
        		+ "No thought, no hurt, no hands to reach\n"
        		+ "No knife, no words, no lie, no cure\n"
        		+ "No need, no hate, no will, no speech";

        final DocumentSentiment documentSentiment = client.analyzeSentiment(document);
        
        SentimentConfidenceScores scores = documentSentiment.getConfidenceScores();
        
        System.out.printf(
            "Recognized document sentiment: %s, positive score: %f, neutral score: %f, negative score: %f.%n",
            documentSentiment.getSentiment(), scores.getPositive(), scores.getNeutral(), scores.getNegative());

        documentSentiment.getSentences().forEach(sentenceSentiment -> {
            SentimentConfidenceScores sentenceScores = sentenceSentiment.getConfidenceScores();
            System.out.printf("Recognized sentence sentiment: %s, positive score: %f, neutral score: %f, negative score: %f.%n",
                sentenceSentiment.getSentiment(), sentenceScores.getPositive(), sentenceScores.getNeutral(), sentenceScores.getNegative());
        });
    }
}