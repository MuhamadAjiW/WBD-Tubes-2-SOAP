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

def subtest():
    soap_action = "http://services.wbdsoap/SubscriptionService/getSubscriptionsByUser"
    url = "http://localhost:8080/api/subscribe"
    headers = {
        "Content-Type": "text/xml;charset=UTF-8",
        "SOAPAction": f'"{soap_action}"',
    }
    body = """
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://services.wbdsoap/">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:getSubscriptionsByUser>
            <user_id>1</user_id>
            <author_id>5</author_id>
        </tns:getSubscriptionsByUser>
    </soapenv:Body>
    </soapenv:Envelope>
    """
    response = requests.post(url, headers=headers, data=body)
    print(response.content)
    return response.content

if __name__ == "__main__":
    response_text = subtest()
    # root = et.fromstring(response_text)
    # message = root.find(".//return/message").text
    # valid = root.find(".//return/valid").text

    # data = root.find(".//return/data")
    # data = et.tostring(data, encoding='utf-8')

    # # Print the extracted values
    # print("Message:", message)
    # print("Valid:", valid)
    # print("Data:", data)