package com.amazon.ata.kindlepublishingservice.dagger;

import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DataAccessModule {

    @Singleton
    @Provides
    public DynamoDBMapper provideDynamoDBMapper() {
        AmazonDynamoDB amazonDynamoDBClient = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
            .withRegion(Regions.US_WEST_2)
            .build();

        return new DynamoDBMapper(amazonDynamoDBClient);
    }

    @Singleton
    @Provides
    public PublishingStatusDao providePublishingStatusDao(DynamoDBMapper dynamoDBMapper) {
        return new PublishingStatusDao(dynamoDBMapper);
    }

    @Singleton
    @Provides
    public CatalogDao catalogDao(DynamoDBMapper dynamoDBMapper) {
        return new CatalogDao(dynamoDBMapper);
    }
}
