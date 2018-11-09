/// <reference path='./element.types.d.ts' /> 

export const element = <ViewAttributes extends {}, LayoutAttributes extends {}> (
  type: string,
  providedAttributes?: ViewAttributes & LayoutAttributes,
  providedChildren?: Array<ElementTypes.ElementNode<unknown, unknown>>
): ElementTypes.ElementNode<ViewAttributes, LayoutAttributes>  => {
  const attributes = typeof providedAttributes !== 'undefined' ? providedAttributes : {};
  const children = typeof providedChildren !== 'undefined' ? providedChildren : [];
  const buildRoot = (): ElementTypes.LayoutMap  => {
    const map = flatMapNodes(type, attributes, children);
    return { map, toJson: () => mapToJson(map) };
  };
  return {
     attributes,
     children,
     type,
     build: buildRoot,
  };
};

const flatMapNodes = (
  type: string,
  attributes: any,
  childElements:  Array<ElementTypes.ElementNode<unknown, unknown>>,
  map: ElementTypes.ElementMap = {},
  keyPrefix: string = ''
): ElementTypes.ElementMap => {
  const key = keyPrefix !== '' ? `${keyPrefix}_${type}` : type;
  const children: any[] = [];
  map[key]  = {
    id: key,
    type,
    attributes,
    children,
  };
  childElements.forEach(childElement => {
    const childKey = `${key}_${childElement.type}` as String
    children.push(childKey);
    flatMapNodes(
      childElement.type,
      childElement.attributes,
      childElement.children,
      map,
      key
    );
  });
  return map;
};

// from object to json array
const mapToJson = (map: any): string => {
  const list = Object.keys(map).map (key => map[key]);
  return JSON.stringify(list);
};
