import pandas as pd
features = ['battery_power', 'ram', 'resolution', 'network_fast', 'screen_area', 'fc']

def func(_df):
    df = pd.DataFrame([_df])
    # Create a new Feature, Screen Area
    df['screen_area'] = df['sc_w'] * df['sc_h']
    # Remove 'sc_h' and 'sc_w' and 'pc'
    df.drop(['sc_h', 'sc_w', 'pc'], axis=1, inplace=True)

    # Replace 'px_width' and 'px_height' with 'resolution' by multiplying them
    df['resolution'] = df['px_width'] * df['px_height']
    df.drop(['px_width', 'px_height'], axis=1, inplace=True)

    # Replace 'three_g' and 'four_g' with 'network_fast'
    df['network_fast'] = df['three_g'] + 2 * df['four_g']
    df.drop(['three_g', 'four_g'], axis=1, inplace=True)
    df = df[features]
    return df