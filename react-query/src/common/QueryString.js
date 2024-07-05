//  url 경로와 params 값 받아서 url로 만들어서 리턴

export const queryString = (url, params) => {
  const queryString = new URLSearchParams(params).toString();
  return `${url}?${queryString}`
}