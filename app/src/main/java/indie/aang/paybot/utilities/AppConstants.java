package indie.aang.paybot.utilities;

import android.os.Environment;

import indie.aang.paybot.BuildConfig;
import indie.aang.paybot.R;

import java.io.File;

public class AppConstants {
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";

    public static final String YES = "YES";
    public static final String NO = "NO";

    public static final int REQUEST_IMAGE_CAPTURE = 10;
    public static final int REQUEST_IMAGE_FROM_GALLERY = 11;

    public static int TABLET_COLUMN_COUNT = 6;
    public static int PHONE_COLUMN_COUNT = 4;

    public static final String SERVER_URL = "http://104.236.220.235/";
    public static final String API_VERSION = "api/";

    public static final int PERMISSION_FINE_LOCATION = 1001;
    public static final int PLACE_PICKER_REQUEST = 1002;
    public static final int PERMISSION_CALL = 1003;
    public static int CAMERA_INTENT = 1004;
    public static int PERMISSION_CAMERA = 1005;
    public static int LOCATION_SETTINGS = 1007;
    public static final int PERMISSION_STORAGE = 1008;



    public static String DEFAULT_PASSWORD = "B8sOXnid2PajBoh9gqZyX3VXdRWyhvV4agA18htg";


    public static final String DATABASE_NAME = R.string.app_name + "_database";

    public static final String DISPLAY_TYPE_FORM = "form";

    public static String [] PHONE_CODES = new String[]{"024", "020", "054", "027", "055", "026", "03", "+23", "233", "050"};


    public static final String USER_ACTIVATED = "ACTIVATED";
    public static final String USER_DEACTIVATED = "DEACTIVATED";
    public static final String USER_SUSPENDED = "SUSPENDED";




    public static String VILLAGE_AGENT = "Village Agent";
    public static String EXPANSION_OFFICER = "Expansion Officer";
    public static String FULFILMENT_PARTNER = "Fulfilment Partner";




    public static int ROLE_AGENT = 3;
    public static int ROLE_FULFILMENT_PARTNER = 5;
    public static int ROLE_EXPANSION_OFFICER = 6;
    public static int ROLE_COMMERCIAL_FARMER = 7;
    public static int ROLE_CAHW = 8;



    public static String ROLE_COMMERCIAL_FARMER_NAME = "COMMERCIAL_FARMER";

    public static String ROLE_AGENT_NAME = "AGENT";



    public static String PERMISSION_AGENT_APP = "AGENT_APP";
    public static String PERMISSION_POPULTRY_APP = "POULTRY_APP";


    public static final String FARMER_TYPE_POULTRY = "Poultry";
    public static final String FARMER_TYPE_COMMERCIAL_POULTRY = "Commercial Poultry";

    public static final String FARMER_TYPE_CROP = "Crop";

    public static final String FARMER_TYPE_LIVESTOCK = "Livestock";

    public static final String LIVESTOCK_COWS = "cattle";
    public static final String LIVESTOCK_SHEEP = "sheep";
    public static final String LIVESTOCK_GOATS = "goats";
    public static final String LIVESTOCK_PIGS = "pigs";


    public static final String BREED_QUESTION_NAME_CATTLE = "select_breed_cattle";
    public static final String BREED_QUESTION_NAME_SHEEP = "select_breed_sheep";
    public static final String BREED_QUESTION_NAME_GOATS = "select_breed_goats";
    public static final String BREED_QUESTION_NAME_PIGS = "select_breed_pigs";
    public static final String BREED_QUESTION_NAME_POULTRY = "select_breed_poultry";


    public static final String CLIENT_TYPE_COMMERCIAL = "COMMERCIAL";
    public static final String CLIENT_TYPE_VILLAGE = "VILLAGE";
    public static final String CLIENT_TYPE_ANY = "ANY";



    public static double COMMISSION_PER_SIGNUPS = 0.15;
    public static double COMMISSION_PER_ORDERS = 0.10;
    public static String CURRENCY = "GHS ";



    public static final String  PAYMENT_CASH = "Cash";
    public static final String  PAYMENT_ON_DELIVERY = "Pay on delivery";

    public static final String  PAYMENT_AIRTEL_MONEY = "Airtel Money";
    public static final String  PAYMENT_VODAFONE_CASH = "Vodafone Cash";
    public static final String  PAYMENT_MOBILE_MONEY = "MTN MoMo";
    public static final String  PAYMENT_VISA = "Visa";
    public static final String  PAYMENT_COWTRIBE_WALLET = "Cowtribe Wallet";
    public static final String  PAYMENT_PAY_LATER = "Pay later";



    public static final String  PAYMENT_STATUS_PENDING = "Pending";
    public static final String  PAYMENT_STATUS_PROCESSING = "Processing";
    public static final String  PAYMENT_STATUS_SUCCESSFUL = "Complete";
    public static final String  PAYMENT_STATUS_FAILED = "Failed";

    public static final String  ORDER_STATUS_DELIVERED = "DELIVERED";
    public static final String  ORDER_STATUS_PENDING = "AWAITING PAYMENT";
    public static final String  ORDER_STATUS_PAID = "NEW";
    public static final String  ORDER_STATUS_COMPLETED = "COMPLETED";
    public static final String  ORDER_STATUS_EN_ROUTE = "EN_ROUTE";
    public static final String  ORDER_STATUS_PROCESSING = "PROCESSING";

    public static final String  ORDER_STATUS_CAMPAIGN = "CAMPAIGN";



    public static final String FARMER_STATUS_NOT_ACTIVATED = "NOT_SUBSCRIBED";
    public static final String FARMER_STATUS_ACTIVATED = "SUBSCRIBED";

    public static final String ITEM_SUBSCRIPTION = "Subscription";
    public static final String ITEM_ORDERS = "Order";




    public static final String FARMER_ACTIVATED = FARMER_STATUS_ACTIVATED;

    public static final String FILTER_ACTIVE_SUBSCRIBERS = "FILTER_ACTIVE_SUBSCRIBERS";




    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";
    public static final String DB_NAME = "fdp.db";
    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String PREF_NAME = "fdp_pref";
    public static final String FARMER_SUBMITTED_YES = "YES";


    public static final long NULL_INDEXL = -1L;
    public static final int NULL_INDEX = -1;

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static String ROOT_DIR = Environment
            .getExternalStorageDirectory() + File.separator + ".Cowtribe-apps/" + BuildConfig.FLAVOR;


    public static String CRASH_REPORTS_DIR = AppConstants.ROOT_DIR + File.separator + "crashReports";
    public static String DATABASE_BACKUP_DIR = AppConstants.ROOT_DIR + File.separator + "databaseBackups";

    public static final String TYPE_TITLE = "title";

    public static final String TYPE_TEXT = "text";
    public static final String TYPE_NUMBER = "number";
    public static final String TYPE_NUMBER_DECIMAL = "decimal";

    public static final String ID = "Id";

    public static final String NULL_STRING = "null";
    public static final String TYPE_LOCATION = "geolocation";
    public static final String TYPE_PHOTO = "photo";

    public static final String TYPE_SELECTABLE = "single select";
    public static final String TYPE_MULTI_SELECTABLE = "multi select";

    public static final String TYPE_TIMEPICKER = "timePicker";
    public static final String TYPE_DATEPICKER = "date";
    public static final String TYPE_DATE_RANGE = "date range";


    public static final String TYPE_MATH_FORMULA = "math formula";
    public static final String TYPE_LOGIC_FORMULA = "logic formula";


    public static final String SHOW = "false";
    public static final String HIDE = "true";


    public static int SYNC_OK = 1;
    public static int SYNC_NOT_OK = 0;

    public static final String FORM_CATTLE = "form_livestock_cattle";
    public static final String FORM_GOATS = "form_livestock_goats";
    public static final String FORM_SHEEP = "form_livestock_sheep";
    public static final String FORM_PIGS = "form_livestock_pigs";
    public static final String FORM_POULTRY = "form_poultry";
    public static final String FORM_CROP = "form_crop";
    public static final String FORM_BASIC_PROFILE = "basic_profile";





    public static final String USERS_TABLE_FIELDS = "[\"id\",\"name\",\"email\",\"phone_number\",\"role_id\",\"country_id\",\"profile_picture\",\"location\",\"gender\",\"status\",\"created_at\",\"updated_at\"]";
    public static final String COUNTRY_TABLE_FIELDS = "[\"id\",\"name\",\"iso_code\",\"currency\",\"exchange_rate\",\"created_at\",\"updated_at\"]";
    public static final String REGION_TABLE_FIELDS = "[\"id\",\"name\",\"country_id\",\"created_at\",\"updated_at\"]";
    public static final String DISTRICTS_TABLE_FIELDS = "[\"id\",\"name\",\"region_id\",\"created_at\",\"updated_at\"]";
    public static final String COMMUNITIES_TABLE_FIELDS = "[\"id\",\"district_id\",\"name\",\"location\",\"form_id\",\"answer_json\",\"landmark\",\"created_at\",\"updated_at\"]";
    public static final String SERVICES_TABLE_FIELDS = "[\"id\",\"name\",\"type\",\"amount\",\"duration\",\"discount\",\"services\",\"created_at\",\"updated_at\"]";

    public static final String FORMS_TABLE_FIELDS = "[\"id\",\"name\",\"label\",\"display_order\",\"country_id\",\"type\",\"hide\",\"created_at\",\"updated_at\"]";
    public static final String QUESTIONS_TABLE_FIELDS = "[\"id\",\"form_id\",\"caption\",\"label\",\"caption_web\",\"display_order\",\"helper_text\",\"options\",\"default_value\",\"type\",\"hide\",\"required\",\"created_at\",\"updated_at\"]";
    public static final String SKIPLOGIC_TABLE_FIELDS = "[\"id\",\"question_id\",\"should_hide\",\"comparing_question_id\",\"value\",\"operator\",\"created_at\",\"updated_at\"]";


    public static final String CATEGORIES_TABLE_FIELDS = "[\"id\",\"name\",\"description\",\"created_at\",\"updated_at\"]";
    public static final String SUB_CATEGORIES_TABLE_FIELDS = "[\"id\",\"category_id\",\"name\",\"description\",\"created_at\",\"updated_at\"]";

    public static final String PRODUCTS_TABLE_FIELDS = "[\"id\",\"country_id\",\"user_id\",\"sub_category_id\",\"name\",\"client\",\"description\",\"type\",\"vaccine_name\"," +
            "\"form_of_application\",\"week\",\"min_delivery_day\",\"max_delivery_day\",\"unit_dose\",\"unit_cost\",\"unit_price\",\"expiry_date\",\"image_url\"," +
            "\"tags\",\"notes\",\"disease\",\"quantity_per_dose\",\"product_type\",\"manufacturer\",\"brand\",\"commission\",\"wholesaler\",\"product_stock_level\",\"product_sku\",\"product_weight\"," +
            "\"discount\",\"created_at\",\"updated_at\"]";


    public static final String DISCOUNTS_TABLE_FIELDS = "[\"id\",\"code\",\"product_id\",\"rate\",\"created_at\",\"updated_at\"]";
    public static final String CAMPAIGNS_TABLE_FIELDS = "[\"id\",\"name\",\"start_date\",\"end_date\",\"delivery_date\",\"status\",\"districts\",\"products\",\"created_at\",\"updated_at\"]";

    public static final String VACCINE_CALENDAR_TABLE_FIELDS = "[\"id\",\"type\",\"product_id\",\"mode_of_administration\",\"cause\",\"quantity_per_head\",\"price_per_head\",\"month\",\"window\",\"week\",\"created_at\",\"updated_at\"]";



    public static final String ACTIVITY_LOGS_TABLE_FIELDS = "[\"id\",\"log_date\",\"duration\",\"description\",\"user_id\",\"farmer_uuid\",\"ip_address\",\"location\",\"created_at\",\"updated_at\"]";






    public static final String FARMERS_TABLE_FIELDS = "[\"id\",\"name\",\"email\",\"phone_number\",\"role_id\",\"country_id\",\"profile_picture\",\"location\",\"gender\",\"status\",\"created_at\",\"updated_at\"]";
    public static final String ORDERS_LOGS_TABLE_FIELDS = "[\"id\",\"name\",\"email\",\"phone_number\",\"role_id\",\"country_id\",\"profile_picture\",\"location\",\"gender\",\"status\",\"created_at\",\"updated_at\"]";


    public static final String MOVEMENT_SLAUGHTERED = "Slaughtered";
    public static final String MOVEMENT_DIED = "Died";
    public static final String MOVEMENT_MARKET = "Market";
    public static final String MOVEMENT_LOST = "Lost";
    public static final String MOVEMENT_TRANSFERRED = "Transferred";




    public static final String SUB_CATEGORY_CATTLE_VACCINES = "cattle vaccines";
    public static final String SUB_CATEGORY_POULTRY_VACCINES = "poultry vaccines";
    public static final String SUB_CATEGORY_GOAST_AND_SHEEP_VACCINES = "goats and sheep vaccines";
    public static final String SUB_CATEGORY_BIRD_FEED = "bird feed";
    public static final String SUB_CATEGORY_GOAST_AND_SHEEP_DEWORMERS = "goats and sheep dewormers";
    public static final String SUB_CATEGORY_POULTRY_DEWORMERS = "poultry dewormers";
    public static final String SUB_CATEGORY_CATTLE_DEWORMERS = "cattle dewormers";


    public static final String TYPE_GROUP = "Group";
    public static final String TYPE_INDIE = "Individual";

    public static int NO_COMMUNITY_ID = 270;
    public static int NO_DISTRICT_ID = 217;
    public static int NO_REGION_ID = 11;

    public static final String FLAG_LOCAL_DB = "localDb";
    public static final String FLAG_CENTRAL_DB = "centralDb";



    private AppConstants(){}

}
