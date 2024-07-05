import React from 'react'
import { useBoardList } from '../queries/useBoardQueries'
import BoardListItem from '../component/BoardListItem';

const BoardList = () => {

  const { isLoading, error, data } = useBoardList();

  if (isLoading) return <div>Loading....</div>;

  if (error) return <div>Error : {error.message}</div>; 

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>순번</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>수정자</th>
            <th>수정일</th>
            <th>비고</th>
          </tr>
        </thead>
        <tbody>
          {data.map((board) => (
            <BoardListItem key={board.idx} data={board} />
          ))}
        </tbody>
      </table>

    </div>
  )
}

export default BoardList