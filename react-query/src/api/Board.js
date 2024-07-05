import { handleResponse } from "../common/FetchResponse";
import { queryString } from "../common/QueryString";
import { SERVER_URL } from "../env"


const BOARD_API = {
  boardList : async() => {
    try {
      const baseURL = `${SERVER_URL}/boardList`;
      const params = {
      };

      const res = await fetch(queryString(baseURL,params),
      {
        method : 'GET',
        headers : {
          'Content-Type' : 'application/json'
        }
      });

      return handleResponse(res);
    } catch (error) {
      throw new Error(error.message);
    }
  },

  board : async(idx) => {
    try {
      const baseURL = `${SERVER_URL}/boardDetail`;
      const params = {
        idx
      };
      const res = await fetch(queryString(baseURL,params),
        {
          method : 'GET',
          headers : {
            'Content-Type' : 'application/json'
          }
        });

      return handleResponse(res);
    } catch (error) {
      throw new Error(error.message);
    }
  },

  createBoard : async(board) => {
    try {
      const baseURL = `${SERVER_URL}/board`;

      const res = await fetch(baseURL,
        {
          method:"POST",
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(board)
        });
      
      if(!res.ok){
        return false;
      }
      return true;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  deleteBoard : async(idx) => {
    try {
      const baseURL = `${SERVER_URL}/board`;
      const params = {
        idx
      };

      const res = await fetch(queryString(baseURL,params),
        {
          method : "DELETE",
          headers : {
            'Content-Type' : 'application/json'
          },
        });

      if(!res.ok){
        return false;
      }
      return true;
    } catch (error) {
      throw new Error(error.message);
    };
  },

  updateBoard : async(board) => {

    try {
      const baseURL = `${SERVER_URL}/board`;
      const res = await fetch(baseURL,{
        method : 'PUT',
        headers : {
          'Content-Type' : 'application/json'
        },
        body: JSON.stringify(board)
      });

      if(!res.ok){
        return false;
      }
      return true;
    } catch (error) {
      throw new Error(error.message);
    }
  }

};



export default BOARD_API;