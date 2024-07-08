import React from 'react'
import BoardList from './pages/BoardList';
import useBoardStore from './store/useBoardStore';
import BoardDetail from './pages/BoardDetail';
import BoardCreate from './pages/BoardCreate';
import LoginPage from './pages/LoginPage';

import { BrowserRouter, Routes, Route} from 'react-router-dom';

function App() {
  const { selectBoardInfo } = useBoardStore();

  const boardDetailData = Object.keys(selectBoardInfo).length > 0;
  

  return (
    <BrowserRouter>

      <LoginPage />

    {/* <div className="User"> */}


      {/* <BoardCreate />

      <BoardList />
      {boardDetailData  && <BoardDetail />  } */}
  {/* </div> */}
    </BrowserRouter>
  );
}

export default App;


//https://medium.com/@jihyun-j/react-query-vs-swr-9470f685c1fe
//참고해보기
