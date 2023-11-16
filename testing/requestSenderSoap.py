import requests
import xml.etree.ElementTree as et

def hellotest():
    soap_action = "http://services.wbdsoap/TestService/hello"
    url = "http://localhost:8080/api/test"
    headers = {
        "Content-Type": "text/xml;charset=UTF-8",
        "SOAPAction": f'"{soap_action}"',
    }
    body = """
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://services.wbdsoap/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:hello>
            <message>Some Message</message>
        </tns:hello>
    </soapenv:Body>
    </soapenv:Envelope>
    """
    response = requests.post(url, headers=headers, data=body)
    print(response.content)
    return response.content

def inserttest():
    soap_action = "http://services.wbdsoap/TestService/insertData"
    url = "http://localhost:8080/api/test"
    headers = {
        "Content-Type": "text/xml;charset=UTF-8",
        "SOAPAction": f'"{soap_action}"',
    }
    body = """
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://services.wbdsoap/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:insertData>
            <description>Some Message</description>
        </tns:insertData>
    </soapenv:Body>
    </soapenv:Envelope>
    """
    response = requests.post(url, headers=headers, data=body)
    print(response.content)
    return response.content

def methodtest():
    soap_action = "http://services.wbdsoap/TestService/someMethod"
    url = "http://localhost:8080/api/test"
    headers = {
        "Content-Type": "text/xml;charset=UTF-8",
        "SOAPAction": f'"{soap_action}"',
    }
    body = """
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://services.wbdsoap/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:someMethod>
            <message>Some Message</message>
        </tns:someMethod>
    </soapenv:Body>
    </soapenv:Envelope>
    """
    response = requests.post(url, headers=headers, data=body)
    print(response.content)
    return response.content

def subtest():
    soap_action = "http://services.wbdsoap/SubscriptionService/subscribeRequest"
    url = "http://localhost:8080/api/subscribe"
    headers = {
        "Content-Type": "text/xml;charset=UTF-8",
        "SOAPAction": f'"{soap_action}"',
        'Authorization': 'Bearer phpinkm'
    }
    body = """
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://services.wbdsoap/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:subscribeRequest>
            <user_id>100</user_id>
            <author_id>100</author_id>
        </tns:subscribeRequest>
    </soapenv:Body>
    </soapenv:Envelope>
    """
    response = requests.post(url, headers=headers, data=body)
    print(response.content)
    return response.content

def updatetest():
    soap_action = "http://services.wbdsoap/SubscriptionService/subscribeUpdate"
    url = "http://localhost:8080/api/subscribe"
    headers = {
        "Content-Type": "text/xml;charset=UTF-8",
        "SOAPAction": f'"{soap_action}"',
        "Authorization": "Bearer restdulugasi"
    }
    body = """
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://services.wbdsoap/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:subscribeUpdate>
            <user_id>11</user_id>
            <author_id>3</author_id>
            <status>REJECT</status>
        </tns:subscribeUpdate>
    </soapenv:Body>
    </soapenv:Envelope>
    """
    response = requests.post(url, headers=headers, data=body)
    print(response.content)
    return response.content

if __name__ == "__main__":
    response_text = updatetest()
    # root = et.fromstring(response_text)
    # message = root.find(".//return/message").text
    # valid = root.find(".//return/valid").text

    # data = root.find(".//return/data")
    # data = et.tostring(data, encoding='utf-8')

    # # Print the extracted values
    # print("Message:", message)
    # print("Valid:", valid)
    # print("Data:", data)