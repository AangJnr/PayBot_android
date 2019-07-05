package indie.aang.paybot.utilities;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public abstract class DatabaseMigrations {


    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE products ADD COLUMN otherName TEXT");
            database.execSQL("ALTER TABLE products ADD COLUMN manufacturer TEXT");
            database.execSQL("ALTER TABLE products ADD COLUMN tags TEXT");
            database.execSQL("ALTER TABLE products ADD COLUMN notes TEXT");
            database.execSQL("ALTER TABLE products ADD COLUMN disease TEXT");
            database.execSQL("ALTER TABLE products ADD COLUMN brand TEXT");
        }
    };


    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE campaigns ADD COLUMN deliveryDate TEXT");
            database.execSQL("ALTER TABLE orders ADD COLUMN deliveryDate TEXT");
            database.execSQL("ALTER TABLE services ADD COLUMN discount DOUBLE");

            //Todo add migration for Vaccine Calendar
            //Todo Add migration for poultry vaccines

            database.execSQL("CREATE TABLE vaccination_calendar " +
                    "(base_id INTEGER, id INTEGER, type TEXT, productId INTEGER, modeOfAdministration TEXT, cause TEXT, recurrence TEXT, quantityPerHead TEXT, pricePerHead DOUBLE, month TEXT, window INTEGER, week INTEGER, createdAt TEXT,  updatedAt TEXT, PRIMARY KEY(base_id))");


            database.execSQL("CREATE TABLE poultry_vaccinations " +
                    "(base_id INTEGER, id INTEGER, poultryPenNo TEXT, vaccinationDate TEXT, vaccineApplied TEXT, numberVaccinated INTEGER, notes TEXT, createdAt TEXT,  updatedAt TEXT,  PRIMARY KEY(base_id))");


        }
    };




}
