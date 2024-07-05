import React from 'react'
import BoardList from './pages/BoardList';
import useBoardStore from './store/useBoardStore';
import BoardDetail from './pages/BoardDetail';
import BoardCreate from './pages/BoardCreate';

function App() {
  const { selectBoardInfo } = useBoardStore();

  const boardDetailData = Object.keys(selectBoardInfo).length > 0;
  

  return (
    <div className="User">
      <BoardCreate />

      <BoardList />
      {boardDetailData  && <BoardDetail />  }
    </div>
  );
}

export default App;


//https://medium.com/@jihyun-j/react-query-vs-swr-9470f685c1fe
//참고해보기
