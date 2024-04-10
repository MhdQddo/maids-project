from flask import Flask, request, jsonify
import random
from func import func
import pandas as pd
import pickle

app = Flask(__name__)

def predict_price(device_details):
    # Load the saved model from disk
    print(type(device_details))
    with open('./final_model.pkl', 'rb') as f:
        loaded_model = pickle.load(f)
        return loaded_model.predict(device_details)
    # predicted_price = random.randint(0, 3)
    # return predicted_price

@app.route('/predict', methods=['POST'])
def predict():
    # Get device details from request body
    device_details = request.json
    
    # Call the predict_price function to get the predicted price
    print("the Body Request:", device_details)
    print(type(device_details))
    df = func(device_details)
    print("the Body Processed Request:", df)
    predicted_price = predict_price(df)
    print(type(list(predicted_price)[0]))
    # Return predicted price as JSON response
    return jsonify({'predicted_price': int(list(predicted_price)[0])})

if __name__ == '__main__':
    app.run(debug=True)
