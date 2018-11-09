
export interface ElementNode<ViewAttributes, LayoutAttributes> {
  attributes: ViewAttributes & LayoutAttributes | {};
  type: string;
  children: Array<ElementNode<unknown, unknown>>;
  build: () => LayoutMap;
}
export interface ElementMap {
  [key: string]: ViewBase<unknown, unknown>
}
export interface LayoutMap {
  map: ElementMap;
  toJson: () => string;
}
export interface ViewBase<ViewAttributes, LayoutAttributes = {}> {
  id: string;
  attributes: ViewAttributes & LayoutAttributes;
  type: string;
  children?: Array<String>;
}
